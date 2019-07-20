package ac.kr.ajou.dirt;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ItemServiceTest {


    @Test
    public void 이름이_Aged_Brie와_Backstage이_모두_아니면_false()
    {
        Item item = new Item("Aged Brie",5,5);
        //Item item2  = new Item("Backstage passes to a TAFKAL80ETC concert",5,5);
        Item[] items  = {item};
        ItemService itemService = new ItemService(items);
        assertFalse(itemService.isValidName(items[0]));
    }

    @Test
    public void 이름이_Aged_Brie와_Backstage와_sulfuras가_모두_아니면_true() {
        Item item = new Item("Sulfuras",5,5);
        Item item2  = new Item("backk",5,5);
        Item[] items  = {item,item2};
        ItemService itemService = new ItemService(items);
        assertTrue(itemService.isInvalidName(items[0]));
        assertTrue(itemService.isInvalidName(items[1]));

    }

    @Test
    public void quality가_0보다크고_50보다_작으면_true() {
        Item item = new Item("Aged Brie",5,5);
        Item item2  = new Item("Backstage passes to a TAFKAL80ETC concert",5,55);
        Item[] items  = {item,item2};
        ItemService itemService = new ItemService(items);
        assertTrue(itemService.isValidQuality(items[0]));
        assertTrue(itemService.isValidQuality(items[1]));

    }

    @Test
    public void quality가_범위안에_있을때_이름이_invalid하면_quality를_줄임()
    {
        Item item = new Item("Aged Brie",5,5);
        Item item2  = new Item("Backstage",5,10);
        Item[] items  = {item,item2};
        ItemService itemService = new ItemService(items);
        itemService.updateQualityByNameAndQulityRange(items[0]);
        int updatedQuality = items[0].quality;
        assertTrue(updatedQuality == 4);
    }

    @Test
    public void quality와_이름이_valid했을때_QualityIncreaseBySellin를_실행하면_true()
    {
        Item item = new Item("Aged Brie",5,5);
        Item item2  = new Item("Backstage",5,10);
        Item[] items  = {item,item2};
        int notUpdatedQuality = item.quality;
        ItemService itemService = new ItemService(items);
        itemService.updateQualityByNameAndQulityRange(items[0]);
        assertThat(items[0].quality,greaterThan(notUpdatedQuality));
    }

    @Test
    public void quality가_valid하고_이름이_brie인경우_quality_1증가하면_true() {
        Item item = new Item("Aged Brie",5,5);
        Item item2  = new Item("Backstage",5,10);
        Item[] items  = {item,item2};
        ItemService itemService = new ItemService(items);
        int notUpdatedQuality = item.quality;
        itemService.QualityIncreaseBySellin(items[0]);
        assertTrue(items[0].quality - notUpdatedQuality == 1);
    }

    @Test
    public void quality가valid하고_sellin이_6보다작고_이름이_backstage인경우_quality_3증가하면_true(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert",5,5);
        Item item2  = new Item("Backstage",5,10);
        Item[] items  = {item,item2};
        ItemService itemService = new ItemService(items);
        int notUpdatedQuality = item.quality;
        itemService.QualityIncreaseBySellin(items[0]);
        assertTrue(items[0].quality - notUpdatedQuality == 3);
    }

    @Test
    public void sellin이_6이상11미만이고_이름이_backstage인경우_quality_2증가하면_true(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert",8,5);
        Item item2  = new Item("Backstage",5,10);
        Item[] items  = {item,item2};
        ItemService itemService = new ItemService(items);
        int notUpdatedQuality = item.quality;
        itemService.QualityIncreaseBySellin(items[0]);
        assertTrue(items[0].quality - notUpdatedQuality == 2);
    }
    @Test
    public void quality가_valid하고_sellin이_0보다작은경우_이름이invalid해서_quality가_1감소하면_true(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert",8,5);
        Item item2  = new Item("Backstage",-2,10);
        Item[] items  = {item,item2};
        ItemService itemService = new ItemService(items);
        int notUpdatedQuality = item2.quality;
        itemService.updateSellinByName(items[1]);
        assertTrue(notUpdatedQuality - items[1].quality == 1);
    }

    @Test
    public void quality가_valid하고_sellin이_0보다작은경우_이름이_backstage여서_quality가_0이되면_true(){
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert",-3,5);
        Item item2  = new Item("Backstage",-2,10);
        Item[] items  = {item,item2};
        ItemService itemService = new ItemService(items);
        itemService.updateSellinByName(items[0]);
        assertThat(items[0].quality,is(0));
    }

    @Test
    public void quality가_valid하고_sellin이_0보다작은경우_이름이_brie여서_quality가_1증가하면_true()
    {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert",-3,5);
        Item item2  = new Item("Aged Brie",-2,10);
        Item[] items  = {item,item2};
        ItemService itemService = new ItemService(items);
        int notUpdatedQuality = items[1].quality;
        itemService.updateSellinByName(items[1]);
        assertTrue(items[1].quality - notUpdatedQuality == 1);
    }

    @Test
    public void 이름이_sulfuras가_아니라면_sellin을_1감소시키면_true() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert",5,5);
        Item item2  = new Item("Aged Brie",-2,10);
        Item[] items  = {item,item2};
        ItemService itemService = new ItemService(items);
        int notUpdatedSellin = items[0].sellIn;
        itemService.sellinDecreaseBySulfuras(items[0]);
        assertTrue(notUpdatedSellin - items[0].sellIn == 1);
    }




}
