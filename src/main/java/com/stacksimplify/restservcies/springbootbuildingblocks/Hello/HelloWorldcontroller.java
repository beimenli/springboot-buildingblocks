package com.stacksimplify.restservcies.springbootbuildingblocks.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldcontroller {
    @Autowired
    private ResourceBundleMessageSource messageSource;
 
	//@RequestMapping(method = RequestMethod.GET,path = "/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		
		return "Hello World";
	}
	
    @GetMapping("/helloworld-bean")
    public UserDetails helloWorldBean() {
        return new UserDetails("Kalyan","Reddy","Hyderabad");
    }
    
   
    @GetMapping("/hello-int")
    public String getMessagesInI18NFormat(@RequestHeader(name = "Accept-Language", required=false) String locale) {
    	return messageSource.getMessage("label.hello", null, new Locale(locale));
    }
    
    @GetMapping("/hello-int2")
    public String getMessagesInI18NFormat2() {
    	return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
    }
}
