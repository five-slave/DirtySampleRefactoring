# Refactoring 과제

## 목표

이번 Refactoring 과제의 목표는 DritySample의 code를 해석하고 unit test를 만든 다음 test에 맞게 code를 refactoring하는 것이다. 

기존 코드는 다음과 같았다.

```java
package ac.kr.ajou.dirt;

class DirtySample {
    Item[] items;

    public DirtySample(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                if (items[i].quality > 0) {
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                        items[i].quality = items[i].quality - 1;
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality = items[i].quality + 1;
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn = items[i].sellIn - 1;
            }

            if (items[i].sellIn < 0) {
                if (!items[i].name.equals("Aged Brie")) {
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (items[i].quality > 0) {
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                                items[i].quality = items[i].quality - 1;
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality;
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;
                    }
                }
            }
        }
    }
} 
```
> DirtySample.java


## 해석

해당 코드를 분석한 결과

1. item.name에 따라 quality 처리가 달랐다. 특별하게 처리되는 이름은 밑에 세가지가 있다.
  
	* __Brie__ : "Aged Brie"
	* __Backstage__ : "Backstage passes to a TAFKAL80ETC concert"
	* __Ragnaros__ : "Sulfuras, Hand of Ragnaros"

	이름이 길기 때문에 각각의 이름들을 왼쪽 부분으로 부르겠다.
    
2. 항상 quality를 뺄 때는 0 보다 커야하고, qulity를 더할 때는 50 보다 작아야 한다는 조건이 붙었다.


### 분석 1
updateQuality() method가 실행됬을 때, 각 이름 마다 처리되는 작업을 간략하게 소개하자면,

#### Brie

1. quality += 1
2. sellIn -= 1
3. if sellIn < 0, quality += 1

#### Backstage

1. if sellIn < 6, quality += 2, else if sellIn < 11, quality +=3, else quality += 1
2. sellIn -= 1
3. if sellIn < 0, quality = 0

#### Ragnaros

1. 아무 작업도 일어나지 않는다.


#### 해당되지 않는 이름

1. quality -= 1
2. sellIn -= 1
3. if sellIn < 0, quality -= 1


### 분석 2
아래와 같이, 직접적인 quality 조정을 메소드화 하고, quality 조정의 valid 검사를 메소드화 하였다.

```java
public void addQualityWithNum(Item item, int num){

        int updateValueOfQuality = item.getQuality() + num;

        if(isValidQulityForUpdatingQuality(item,num))
            item.setQuality(updateValueOfQuality);

}

public boolean isValidQulityForUpdatingQuality(Item item, int num){

        int currentQuality = item.getQuality();

        if(num>=0 && currentQuality<50)
            return true;
        else if(num<=0 && currentQuality>0)
            return true;

        return false;
}
```

## 테스트
테스트의 경우, 앞서 분석한 토대로 구현을 하였다. 이름이 Brie, Backstage, Ragnaros와 이 세가지에 해당이 되지 않는 경우로 각각 나누어서 구현하였다. 그래서 이름에 따라 Sellin과 Quality의 valid 여부와 함수를 실행하면서 valid에서 invalid로, invalid에서 valid로 변화한 경우를 고려하여 각각의 test case를 구현하였다. 
또한 Backstage의 경우, Sellin의 범위에 따라 Quality의 변화가 달라지기 때문에 이 부분도 test를 구현하였다. 
마지막으로 Sellin과 Quality와 상관없이 이름이 Ragnaros가 아닌 경우, Sellin의 변화에 대해서도 test case를 구현하였다. 

## 결론

이로부터, _해당되지 않는 이름_ 에 대한 서비스 (ItemService) 를 베이스로 하고, BrieItemService, BackstageItemService, RagnarosItemService 4 개의 클레스를 만들고, ItemmService를 제외한 나머지 클레스들이 ItemService를 상속하게 하였다. 이렇게 refactoring한 이유는 실제 qualtiy를 빼고 더하는 작업은 갖고 이름에 따라 얼만큼 더하고 빼는 것만 다르기 때문이다.

```java
public void updateQuality(Item item){

    addQualityWithNum(item,-1);

    addSellInWithNum(item,-1);
    
    if(item.getSellIn()<0){
        addQualityWithNum(item,-1);
    }
}
```
> ItemService.java

```java
public class BrieItemService extends ItemService{

    @Override
    public void updateQuality(Item item){
        addQualityWithNum(item,1);
        
        addSellInWithNum(item,-1);
        
        if(item.getSellIn()<0){
            addQualityWithNum(item,1);
        }
    }
}
```
> BrieItemService

위 workflow를 담고 있는 해당 upateQuality() 메소드를 다른 클래스에서 Override 하여 각각 이름에 맞게 바꿨다.

```java
public void updateQuality() {
    for (int i = 0; i < items.size(); i++) {

        if(isBrie(items.get(i))){
            itemService = new BrieItemService();
            itemService.updateQuality(items.get(i));

        }else if(isBackstage(items.get(i))){
            itemService = new BackstageItemService();
            itemService.updateQuality(items.get(i));

        }else if(isRagnaros(items.get(i))){
            itemService = new RagnarosItemService();
            itemService.updateQuality(items.get(i));
        }else{
            itemService = new ItemService();
            itemService.updateQuality(items.get(i));
        }
    }
}
```
> DirtySample.java

DirtySample 에서는 각 아이템이 어떤 서비스에 속하는지 구별하도록 작성하였다.
