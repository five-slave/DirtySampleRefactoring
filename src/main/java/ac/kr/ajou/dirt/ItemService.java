package ac.kr.ajou.dirt;

import lombok.Data;

@Data
public class ItemService {

    private Item[] items;

    public ItemService(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {

            updateQualityByItemName(items[i]);

            if(!isRagnaros(items[i]))
                addSellInWithNum(items[i],-1);

            updateQualityByItemNameWhenSellInLessThan0(items[i]);

//            if (!items[i].name.equals("Aged Brie")
//                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                if (items[i].quality > 0) {
//                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
//                        items[i].quality = items[i].quality - 1;
//                    }
//                }
//            } else {
//                if (items[i].quality < 50) {
//                    items[i].quality = items[i].quality + 1;
//
//                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
//                        if (items[i].sellIn < 11) {
//
//                            items[i].quality =+ 1;
//
//                        }else if(items[i].sellIn< 6){
//
//                            items[i].quality =+ 2;
//                        }
//                    }
//                }
//            }
//
//            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
//                items[i].sellIn = items[i].sellIn - 1;
//            }
//
//
//            if (items[i].sellIn < 0) {
//                if (!items[i].name.equals("Aged Brie")) {
//                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) { //
//                        if (items[i].quality > 0) {
//                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
//                                items[i].quality = items[i].quality - 1;
//                            }
//                        }
//                    } else {
//                        items[i].quality = items[i].quality - items[i].quality;
//                    }
//                } else {    // sellIn < 0 인 Brie 의 quality +1;
//                    if (items[i].quality < 50) {
//                        items[i].quality = items[i].quality + 1;
//                    }
//                }
//            }
            // 아무것도 아니면 quality -1;
            // Brie Or Backstage이면 quality + 1, But Backstage sellIn < 11 , quality+2, sellIn < 6 , quality + 3.
            // Ragnaros 가 아니면 sellIn -1;
            // sellIn < 0 인 Brie 의 quality + 1;
            // sellIn < 0 인 Backstage 의 qualtiy = 0;
            // sellIn < 0 인 이무것도 아니면 quality - 1;

        }
    }

    public void addQualityWithNum(Item item, int num){

        int updateValueOfQuality = item.getQuality() + num;

        if(isValidQulityForUpdatingQuality(item))
            item.setQuality(updateValueOfQuality);

    }

    public void addSellInWithNum(Item item,int num){

        int updateValueOfSellIn = item.getQuality() + num;

        item.setQuality(updateValueOfSellIn);
    }

    public void updateQualityByItemNameWhenSellInLessThan0(Item item){
        int currentSellIn = item.getSellIn();

        if(currentSellIn<0){

            if(isBrie(item))
                addQualityWithNum(item,1);
            else if (isBackstage(item))
                addQualityWithNum(item, -item.getQuality());
            else if (isRagnaros(item));
            else
                addQualityWithNum(item,-1);
        }

    }

    public void updateQualityByItemName(Item item){

        if(isBrie(item)){
            addQualityWithNum(item,1);

        }else if(isBackstage(item)){
            applyAdditionalQualityBySellIn(item);
        }else if(isRagnaros(item));
        else
            addQualityWithNum(item,-1);

    }

    public void applyAdditionalQualityBySellIn(Item item){
        int currentSellIn = item.getSellIn();

        if(currentSellIn<6)
            addQualityWithNum(item,3);
        else if(currentSellIn< 11)
            addQualityWithNum(item,2);
        else
            addQualityWithNum(item, 1);

    }

    public boolean isValidQulityForUpdatingQuality(Item item){
        int currentQuality = item.getQuality();

        if(currentQuality>0 && currentQuality<50)
            return true;

        return false;
    }

    public boolean isBrie(Item item){
        if(item.getName().equals("Aged Brie"))
            return true;
        return false;
    }

    public boolean isBackstage(Item item){
        if(item.getName().equals("Backstage passes to a TAFKAL80ETC concert"))
            return true;
        return false;
    }

    public boolean isRagnaros(Item item){
        if(item.getName().equals("Sulfuras, Hand of Ragnaros"))
            return true;
        return false;
    }

}