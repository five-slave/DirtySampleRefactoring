package ac.kr.ajou.dirt;

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
