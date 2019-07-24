package ac.kr.ajou.dirt;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class DirtySampleTest {

    private DirtySample dirtySample;

    public Item findItemByName(List<Item> items, String name){


        for(int i =0;i<items.size();i++){
            if(items.get(i).getName().equals(name)){
                return items.get(i);

            }
        }

        return null;

    }

    @Before
    public void set_up(){
        Item brie = new Item("Aged Brie",0,0);
        Item backstage = new Item("Backstage passes to a TAFKAL80ETC concert",0,0);
        Item ragnaros = new Item("Sulfuras, Hand of Ragnaros",0,0);
        Item any = new Item("any", 0,0);

        List<Item> items = new ArrayList<>(Arrays.asList(brie,backstage,ragnaros,any));

        dirtySample = new DirtySample(items);
    }

    @Test
    public void 이름이_Aged_Brie또는_Backstage인데_quality가_50보다_큰경우_Quality는_변하지않는다()
    {
        List<Item> items = dirtySample.getItems();
        Item brie = findItemByName(items,"Aged Brie");
        brie.setQuality(100);
        int number = brie.getQuality();
        dirtySample.updateQuality();
        int updateBrieQuality = brie.getQuality();
        assertTrue(updateBrieQuality  == number );

    }

    @Test
    public void 이름이_Aged_Brie또는_Backstage이면_Sell_In은_1_감소한다()
    {
        List<Item> items = dirtySample.getItems();
        Item brie = findItemByName(items,"Aged Brie");
        Item backstage = findItemByName(items,"Backstage passes to a TAFKAL80ETC concert");
        int brieSellIn = brie.getSellIn();
        int backstageSellin = backstage.getSellIn();
        dirtySample.updateQuality();
        int updateBrieSellin = brie.getSellIn();
        int updateBackstageSellin = backstage.getSellIn();
        assertTrue(updateBrieSellin  == brieSellIn - 1 );
        assertTrue(updateBrieSellin  == backstageSellin - 1 );

    }

    @Test
    public void Aged_Brie가_Sell_In이0이고_Quality가1일때_updateQaulity이후_Quality는3이어야한다(){

        List<Item> items = dirtySample.getItems();
        Item brie = findItemByName(items,"Aged Brie");

        brie.setQuality(1);
        brie.setSellIn(0);

        dirtySample.updateQuality();

        assertThat(brie.getQuality(),is(3));

    }

    @Test
    public void Aged_Brie가_Sell_In이1이고_Quality가1일때_updateQaulity이후_Quality는2이어야한다(){
        List<Item> items = dirtySample.getItems();
        Item brie = findItemByName(items,"Aged Brie");

        brie.setQuality(1);
        brie.setSellIn(1);

        dirtySample.updateQuality();

        assertThat(brie.getQuality(),is(2));
    }

    @Test
    public void Aged_Brie가_Sell_In이0이고_Quality가49일때_updateQaulity이후_Quality는50이어야한다(){
        List<Item> items = dirtySample.getItems();
        Item brie = findItemByName(items,"Aged Brie");

        brie.setQuality(49);
        brie.setSellIn(0);

        dirtySample.updateQuality();

        assertThat(brie.getQuality(),is(50));
    }

    @Test
    public void Backstage가_Sell_In이0이고_Quality가30일때_updateQaulity이후_Quality는0이어야한다(){
        List<Item> items = dirtySample.getItems();
        Item backstage = findItemByName(items,"Backstage passes to a TAFKAL80ETC concert");

        backstage.setQuality(30);
        backstage.setSellIn(0);

        dirtySample.updateQuality();

        assertThat(backstage.getQuality(),is(0));
    }

    @Test
    public void Backstage가_Sell_In이3이고_Quality가30일때_updateQaulity이후_Quality는33이어야한다(){
        List<Item> items = dirtySample.getItems();
        Item backstage = findItemByName(items,"Backstage passes to a TAFKAL80ETC concert");

        backstage.setQuality(30);
        backstage.setSellIn(3);

        dirtySample.updateQuality();

        assertThat(backstage.getQuality(),is(33));
    }

    @Test
    public void Backstage가_Sell_In이9이고_Quality가30일때_updateQaulity이후_Quality는32이어야한다(){
        List<Item> items = dirtySample.getItems();
        Item backstage = findItemByName(items,"Backstage passes to a TAFKAL80ETC concert");

        backstage.setQuality(30);
        backstage.setSellIn(9);

        dirtySample.updateQuality();

        assertThat(backstage.getQuality(),is(32));
    }

    @Test
    public void Backstage가_Sell_In이20이고_Quality가30일때_updateQaulity이후_Quality는31이어야한다(){
        List<Item> items = dirtySample.getItems();
        Item backstage = findItemByName(items,"Backstage passes to a TAFKAL80ETC concert");

        backstage.setQuality(30);
        backstage.setSellIn(20);

        dirtySample.updateQuality();

        assertThat(backstage.getQuality(),is(31));
    }

    @Test
    public void backstage가_Sell_in이10이고_quality가_50보다_큰경우_Quality는_변하지않는다() {
        List<Item> items = dirtySample.getItems();
        Item backstage = findItemByName(items,"Backstage passes to a TAFKAL80ETC concert");
        backstage.setQuality(55);
        backstage.setSellIn(10);
        int number = backstage.getQuality();
        dirtySample.updateQuality();
        int updateBackstageQuality = backstage.getQuality();
        assertThat(updateBackstageQuality,is(number));

    }

    @Test
    public void Ragnaros가_Sell_In이0이고_Quality가1일때_updateQaulity이후_Quality는1이어야한다(){
        List<Item> items = dirtySample.getItems();
        Item ragnaros = findItemByName(items,"Sulfuras, Hand of Ragnaros");

        ragnaros.setQuality(1);
        ragnaros.setSellIn(0);

        dirtySample.updateQuality();

        assertThat(ragnaros.getQuality(),is(1));
    }

    @Test
    public void Ragnaros가_Sell_In이음수3이고_Quality가1일때_updateQaulity이후_Quality는1이어야한다(){
        List<Item> items = dirtySample.getItems();
        Item ragnaros = findItemByName(items,"Sulfuras, Hand of Ragnaros");

        ragnaros.setQuality(1);
        ragnaros.setSellIn(-3);

        dirtySample.updateQuality();

        assertThat(ragnaros.getQuality(),is(1));
    }

    @Test
    public void 일반항목의_Sell_In이0이고_Quality가1일때_updateQaulity이후_Quality는1이어야한다(){
        List<Item> items = dirtySample.getItems();
        Item any = findItemByName(items,"any");

        any.setQuality(1);
        any.setSellIn(0);

        dirtySample.updateQuality();

        assertThat(any.getQuality(),is(0));
    }

    @Test
    public void 일반항목의_Sell_In이3이고_Quality가10일때_updateQaulity이후_Quality는1이어야한다(){
        List<Item> items = dirtySample.getItems();
        Item any = findItemByName(items,"any");

        any.setQuality(10);
        any.setSellIn(3);

        dirtySample.updateQuality();

        assertThat(any.getQuality(),is(9));
    }

    @Test
    public void 일반항목의_Sell_In이0이고_Quality가10일때_updateQaulity이후_Quality는8이어야한다(){
        List<Item> items = dirtySample.getItems();
        Item any = findItemByName(items,"any");

        any.setQuality(10);
        any.setSellIn(0);

        dirtySample.updateQuality();

        assertThat(any.getQuality(),is(8));
    }


  @Test
    public void 이름이_일반항목이고_quality가_0일때_updateQuality를하면_Quality는_변하지않는다() {
        List<Item> items = dirtySample.getItems();
        Item any = findItemByName(items,"any");
        any.setQuality(0);
        int number = any.getQuality();
        dirtySample.updateQuality();
        int updateAnyQuality = any.getQuality();
        assertThat(updateAnyQuality,is(number));



    @Test
    public void DirtySample안에있는_boolean_isBrie_검증(){
        List<Item> items = dirtySample.getItems();
        Item brie = findItemByName(items,"Aged Brie");
        assertThat(dirtySample.isBrie(brie), is(true));
    }
    @Test
    public void DirtySample안에있는_boolean_isBackstage_검증(){
        List<Item> items = dirtySample.getItems();
        Item backstage = findItemByName(items,"Backstage passes to a TAFKAL80ETC concert");
        assertThat(dirtySample.isBackstage(backstage), is(true));
    }
    @Test
    public void DirtySample안에있는_boolean_isRagnaros_검증(){
        List<Item> items = dirtySample.getItems();
        Item ragnaros = findItemByName(items,"Sulfuras, Hand of Ragnaros");
        assertThat(dirtySample.isRagnaros(ragnaros), is(true));
    }

    @Test
    public void ItemService안에있는_addQualityWithNum_검증(){
        List<Item> items = dirtySample.getItems();

        ItemService itemService = new ItemService();

        Item brie = findItemByName(items,"Aged Brie");
        Item backstage = findItemByName(items,"Backstage passes to a TAFKAL80ETC concert");
        Item ragnaros = findItemByName(items,"Sulfuras, Hand of Ragnaros");

        brie.setQuality(0);
        backstage.setQuality(50);
        ragnaros.setQuality(25);

        itemService.addQualityWithNum(brie, 1);
        itemService.addQualityWithNum(backstage, 1);
        itemService.addQualityWithNum(ragnaros, 1);

        assertThat(brie.getQuality(), is(1));
        assertThat(backstage.getQuality(), is(50));
        assertThat(ragnaros.getQuality(), is(26));

    }
    @Test
    public void ItemService안에있는_addSellInWithNum_검증(){

        List<Item> items = dirtySample.getItems();

        ItemService itemService = new ItemService();

        Item brie = findItemByName(items,"Aged Brie");
        Item backstage = findItemByName(items,"Backstage passes to a TAFKAL80ETC concert");
        Item ragnaros = findItemByName(items,"Sulfuras, Hand of Ragnaros");

        brie.setSellIn(0);
        backstage.setSellIn(-1);
        ragnaros.setSellIn(1);

        itemService.addSellInWithNum(brie, 1);
        itemService.addSellInWithNum(backstage, 1);
        itemService.addSellInWithNum(ragnaros, 1);

        assertThat(brie.getSellIn(), is(1));
        assertThat(backstage.getSellIn(), is(0));
        assertThat(ragnaros.getSellIn(), is(2));

    }
    @Test
    public void ItemService안에있는_isValidQualityForUpdatingQuality_검증(){

        List<Item> items = dirtySample.getItems();

        ItemService itemService = new ItemService();

        Item brie = findItemByName(items,"Aged Brie");
        Item any = findItemByName(items, "any");

        brie.setQuality(0);
        any.setQuality(50);

        assertThat(itemService.isValidQulityForUpdatingQuality(brie, 10), is(true));
        assertThat(itemService.isValidQulityForUpdatingQuality(any, 10), is(false));



    }
}
