package ac.kr.ajou.dirt;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DirtySampleTest {

    @Test
    public void AgedBrieItemTest(){

        List<Item> items = new ArrayList<>();

        items.add(new Item("Aged Brie",0,0));
        DirtySample dirtySample = new DirtySample(items);
        dirtySample.updateQuality();
        assertThat(items.get(0).getQuality(),is(2));

    }

}

