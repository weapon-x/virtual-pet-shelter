package wcci.julian;

import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class VirtualPetShelterTest
{
	VirtualPetShelter vps = new VirtualPetShelter();
	Collection<VirtualPet> pets = vps.getPets();
	
	@Before
	public void addPets()
	{
		VirtualPet pet = new VirtualPet("Bimmy", "Bimmy likes Journey");
		VirtualPet pet1 = new VirtualPet("Timmy", "Timmy is stoopid");
		VirtualPet pet2 = new VirtualPet("Jimmy", "Jimmy is okay");
		
		vps.addPet(pet);
		vps.addPet(pet1);
		vps.addPet(pet2);
	}
	
	@Test
	public void shouldReturnASpecificPet()
	{		
		assertThat(vps.returnSpecificPet("Bimmy").getName(), is("Bimmy") );
	}
	
	@Test
	public void shouldReturnAllPets()
	{
		int petShelterSize = pets.size();
		int expectedSize = 3;
		
		assertThat(petShelterSize, is(expectedSize));
	}
	
	@Test
	public void shouldBeAbleToAddAPet()
	{
		vps.addPet(new VirtualPet("Jojo", "Jojo's Bizarre Adventure"));
		int expectedSize = vps.getPets().size();
		
		assertThat(4, is(expectedSize));
	}
	
	@Test
	public void shouldBeAbleToAdoptPet()
	{
		VirtualPet petJoanne = new VirtualPet("Joanne", "Poor Joanne");
		vps.addPet(petJoanne);
		
		vps.adoptPet(petJoanne);
		int expectedSize = 3;
		
		assertThat(vps.getPets().size(), is(expectedSize));
		
	}
	
	@Test
	public void shouldFeedAllPets()
	{
		int originalTotalHungerValues = 0;
		int expectedTotalHungerValues = 0;
		int expectedDifferenceAfterFeedingAllPets = 60;
		
		for (VirtualPet pet : vps.getPets())
		{
			originalTotalHungerValues += pet.getHunger();
		}
		
		vps.feedAll();
		for (VirtualPet pet : vps.getPets())
		{
			expectedTotalHungerValues += pet.getHunger();
		}
		
		
		assertThat(expectedDifferenceAfterFeedingAllPets, is(expectedTotalHungerValues - originalTotalHungerValues));
	}
	
	@Test
	public void shouldPlayWithAllPetsAndLowerEnergyValuesOfEachPet()
	{
		//vps.playWithAll();
		int expectedTotalFunValues = 0;
		int expectedTotalEnergyValues = 0;
		
		int originalTotalFunValuesOfPets = 0;
		int originalTotalEnergyValuesOfPets = 0;
		
		for (VirtualPet pet : vps.getPets())
		{
			originalTotalFunValuesOfPets += pet.getFun();
			originalTotalEnergyValuesOfPets += pet.getEnergy();
		}
		
		vps.playWithAll();
		
		for(VirtualPet pet : vps.getPets())
		{
			expectedTotalFunValues += pet.getFun();
			expectedTotalEnergyValues += pet.getEnergy();
		}
		
		int expectedDifferenceOfAlteredFunValues = expectedTotalFunValues - originalTotalFunValuesOfPets;
		int expectedDifferenceOfAlteredEnergyValues = expectedTotalEnergyValues - originalTotalEnergyValuesOfPets;
		
		assertThat(60, is(expectedDifferenceOfAlteredFunValues));
		
		assertThat(-60, is(expectedDifferenceOfAlteredEnergyValues));
	}
	
	
}
