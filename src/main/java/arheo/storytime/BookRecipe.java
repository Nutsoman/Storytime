package arheo.storytime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;

public class BookRecipe implements IRecipe {

	protected static Random rand = new Random();
	
	
	@Override
	public boolean matches(InventoryCrafting grid, World world) {
		ItemStack book = grid.getStackInSlot(0);
		ItemStack stick = grid.getStackInSlot(1);
		if (book!=null&&stick!=null) {
			if (book.getItem()==Items.book&&stick.getItem()==Items.stick) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting grid) {
		ItemStack out = new ItemStack (Items.written_book);
		NBTTagCompound tag = new NBTTagCompound();
		Theme theme = Theme.values()[rand.nextInt(Theme.values().length-1)+1];
		Story story = new Story(theme);
		tag.setString("author",story.author);
		tag.setString("title",story.title);
		tag.setInteger("generation", 0);
		NBTTagList pages = new NBTTagList();
		List<String> text = ubermethod();
		for (String pagetext:text) {
			NBTTagString page = new NBTTagString(pagetext);
			pages.appendTag(page);
		}
		tag.setTag("pages", pages);
		
		out.setTagCompound(tag);
		return out;
	}
	

	@Override
	public int getRecipeSize() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> ubermethod() {
		List<String> pages = new ArrayList<String>();
		String dave = "Dave";
		pages.add(dave);
		return pages;
	}
	
	
	
	
	
	
	
	
}
