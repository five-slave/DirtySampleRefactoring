package ac.kr.ajou.dirt;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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
        Item any = new Item("any",0,0);

        List<Item> items = new ArrayList<>(Arrays.asList(brie,backstage,ragnaros,any));

        dirtySample = new DirtySample(items);
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
}
