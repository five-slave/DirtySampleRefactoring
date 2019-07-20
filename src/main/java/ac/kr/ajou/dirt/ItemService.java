package ac.kr.ajou.dirt;

class ItemService {
    Item[] items;

    public ItemService(Item[] items) {
        this.items = items;
    }

    String sulfuras = "Sulfuras, Hand of Ragnaros";
    String backStage = "Backstage passes to a TAFKAL80ETC concert";
    String brie = "Aged Brie";

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateQualityByNameAndQulityRange(items[i]);

            sellinDecreaseBySulfuras(items[i]);

            updateSellinByName(items[i]);

        }
    }

    public void sellinDecreaseBySulfuras(Item item) {
        if (!item.name.equals(sulfuras)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    public boolean isValidName(Item item) {
        return item.name.equals(brie)
                || item.name.equals(backStage);
    }

    public boolean isInvalidName(Item item) {
        if (!isValidName(item) && !item.name.equals(sulfuras))
            return true;
        return false;
    }

    public void updateQualityByNameAndQulityRange(Item item) {
        if (isValidQuality(item)) {
            if (isInvalidName(item)) {
                item.quality = item.quality - 1;
            }
            if (isValidName(item)) {
                QualityIncreaseBySellin(item);
            }
        }
    }

    public void QualityIncreaseBySellin(Item item) {
        if (item.name.equals(backStage)) {
            if (item.sellIn < 6) item.quality = item.quality + 3;
            if (item.sellIn >= 6 && item.sellIn < 11) item.quality = item.quality + 2;
        } else item.quality = item.quality + 1;

    }

    public void updateSellinByName(Item item) {
        if (isValidQuality(item) && item.sellIn < 0) {
            if (isInvalidName(item))
                item.quality = item.quality - 1;
            if (item.name.equals(backStage))
                item.quality = 0;
            if (item.name.equals(brie))
                item.quality = item.quality + 1;

        }
    }

    public boolean isValidQuality(Item item) {
        if (item.quality > 0 && item.quality < 50)
            return true;
        return false;
    }

}