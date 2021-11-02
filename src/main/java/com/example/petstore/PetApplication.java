package com.example.petstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("pets")
@EnableAspectJAutoProxy
public class PetApplication {

	static HashMap<String, Pet> pets = new HashMap<String, Pet>();

	public static void main(String[] args) {
		pets.put(Dog.class.getSimpleName().toLowerCase(), new Dog());
		pets.put(Cat.class.getSimpleName().toLowerCase(), new Cat());

		SpringApplication.run(PetApplication.class, args);
	}


	@RequestMapping(method = RequestMethod.GET, path="/")
	public String listPets(){
		final StringBuffer result = new StringBuffer();

		result.append("<h1> PET STORE </h1>");

		pets.values().forEach(pet -> {result.append("<li>"+pet);});

		result.append("<p> 총 페이지뷰:"+ HomeAdvice.getPageView());
		return result.toString();
	}

	@RequestMapping(method = RequestMethod.GET, path="{petId}")
	public String showPet(@PathVariable(value = "petId") String petId){
		StringBuffer result = new StringBuffer();
		Pet thePet = pets.get(petId);

		if(thePet==null) return null;

		result.append("<h1>"+petId+"</h1>");

		result.append("<br>에너지: " + thePet.getEnergy());

		result.append("<li> <a href='"+petId+"/feed'>먹이주기</a>");
		result.append("<li> <a href='"+petId+"/sleep'>재우기</a>");
		result.append("<li> <a href='"+petId+"/cart'>입양하기</a>");

		if(thePet instanceof Groomable)
			result.append("<li> <a href='"+petId+"/groom'>그루밍하기</a>");

		return result.toString();
	}

	@Autowired
	ICart cart;


	@RequestMapping(method = RequestMethod.GET, path="{petId}/cart")
	public String addToCart(@PathVariable(value = "petId") String petId) throws Exception{
		//StringBuffer result = new StringBuffer();
		Pet thePet = pets.get(petId);

		cart.add(thePet);  // Separation of Concerns. Dependency Inversion Principle.

		return "성공적으로 입양했습니다<br>" + cart;
	}

	@RequestMapping(method = RequestMethod.GET, path="{petId}/feed")
	public String feed(@PathVariable(value = "petId") String petId){
		StringBuffer result = new StringBuffer();
		Pet thePet = pets.get(petId);

		thePet.eat();

		return "맛있는 거 먹였습니다.";
	}


	@RequestMapping(method = RequestMethod.GET, path="{petId}/groom")
	public String groom(@PathVariable(value = "petId") String petId){
		Pet thePet = pets.get(petId);

		if(thePet instanceof Groomable){
			return ((Groomable)thePet).grooming();
		}


		return "그루밍이 불가능한 Pet 입니다";
	}
}
