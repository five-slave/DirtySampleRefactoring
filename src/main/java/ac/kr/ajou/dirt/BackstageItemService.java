package ac.kr.ajou.dirt;

public class BackstageItemService extends ItemService {

    @Override
    public void updateQuality(Item item){
        int currentSellIn = item.getSellIn();

        addQualityWithNum(item,1);

        if(currentSellIn< 11)
            addQualityWithNum(item,1);
        if(currentSellIn<6)
            addQualityWithNum(item,1);

        addSellInWithNum(item,-1);

        if(item.getSellIn()<0){
            addQualityWithNum(item, -item.getQuality());
        }
    }
}
