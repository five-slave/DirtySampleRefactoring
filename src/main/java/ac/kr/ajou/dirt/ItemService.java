package ac.kr.ajou.dirt;

public class ItemService {

    public void updateQuality(Item item){

        addQualityWithNum(item,-1);

        addSellInWithNum(item,-1);
        if(item.getSellIn()<0){
            addQualityWithNum(item,-1);
        }
    }

    public void addQualityWithNum(Item item, int num){

        int updateValueOfQuality = item.getQuality() + num;

        if(isValidQulityForUpdatingQuality(item,num))
            item.setQuality(updateValueOfQuality);

    }

    public void addSellInWithNum(Item item,int num){

        int updateValueOfSellIn = item.getSellIn() + num;

        item.setSellIn(updateValueOfSellIn);
    }

    public boolean isValidQulityForUpdatingQuality(Item item, int num){

        int currentQuality = item.getQuality();

        if(num>=0 && currentQuality<50)
            return true;
        else if(num<=0 && currentQuality<0)
            return true;

        return false;
    }

}
