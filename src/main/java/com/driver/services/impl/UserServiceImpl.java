package com.driver.services.impl;

import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.model.User;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository3;
    @Autowired
    ServiceProviderRepository serviceProviderRepository3;
    @Autowired
    CountryRepository countryRepository3;

    @Override
    public User register(String username, String password, String countryName) throws Exception{
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setConnected(false);
        user.setMaskedIp(null);
        String ip="";
        if(countryName=="IND" ||countryName=="ind")
            ip=ip+"001.";
        if(countryName=="USA" ||countryName=="usa")
            ip=ip+"002.";
        if(countryName=="AUS" ||countryName=="aus")
            ip=ip+"003.";
        if(countryName=="CHI" ||countryName=="chi")
            ip=ip+"004.";
        if(countryName=="JPN" ||countryName=="jpn")
            ip=ip+"005.";
     String userid= String.valueOf(user.getId());
     ip=ip+userid;
      user.setOriginalIp(ip);
        Country country=user.getCountry();
        country.setCountryName(CountryName.valueOf(countryName));
        country.setUser(user);
        user.setCountry(country);
        userRepository3.save(user);
        return user;


    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) {
        User user=userRepository3.findById(userId).get();
        ServiceProvider serviceProvider=serviceProviderRepository3.findById(serviceProviderId).get();
        List<ServiceProvider> serviceProviderList=user.getServiceProviderList();
        List<User>userList=serviceProvider.getUsers();
        serviceProviderList.add(serviceProvider);
        userList.add(user);
        userRepository3.save(user);
        return user;
    }
}
