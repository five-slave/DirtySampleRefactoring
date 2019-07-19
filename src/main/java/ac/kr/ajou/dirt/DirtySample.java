package ac.kr.ajou.dirt;

import lombok.Data;

import java.util.List;

@Data
public class DirtySample {

    private List<Item> items;
    private ItemService itemService;

    public DirtySample(List<Item> items) {
        this.items = items;
    }

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

            // 아무것도 아니면 quality -1;
            // Brie Or Backstage이면 quality + 1, But Backstage sellIn < 11 , quality+2, sellIn < 6 , quality + 3.
            // Ragnaros 가 아니면 sellIn -1;
            // sellIn < 0 인 Brie 의 quality + 1;
            // sellIn < 0 인 Backstage 의 qualtiy = 0;
            // sellIn < 0 인 이무것도 아니면 quality - 1;


        }
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