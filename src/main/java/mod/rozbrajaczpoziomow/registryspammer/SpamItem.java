package mod.rozbrajaczpoziomow.registryspammer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class SpamItem extends Item {
    private final int n, outOf;
    public SpamItem(final int n, final int outOf) {
        super(new FabricItemSettings());
        this.n = n;
        this.outOf = outOf;
    }

    @Override
    public Text getName() {
        return Text.of("Registry Spammer #" + n + "/" + outOf);
    }

    @Override
    public Text getName(ItemStack stack) {
        return getName();
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return n % 2 == 0;
    }
}
