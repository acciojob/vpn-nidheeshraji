package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ConnectionRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    UserRepository userRepository2;
    @Autowired
    ServiceProviderRepository serviceProviderRepository2;
    @Autowired
    ConnectionRepository connectionRepository2;

    @Override
    public User connect(int userId, String countryName) throws Exception{
        User user=userRepository2.findById(userId).get();
        if(user.isConnected())
        {
            throw new Exception("connected");
        }
        String originalCountry=user.getCountry().getCountryName().toString();

        //coded country name

      /* String codedcountry="";
        if(countryName=="IND")
            codedcountry="001";
        if(countryName=="IND")
            codedcountry="001";
        if(countryName=="IND")
            codedcountry="001";
        if(countryName=="IND")
            codedcountry="001";
        */




        if(countryName==originalCountry)
            return user;


        List<ServiceProvider> serviceProviderList=user.getServiceProviderList();
        if(serviceProviderList==null)
        {
            throw  new Exception("Not possible");
        }
        ServiceProvider serviceProvider=null;
        Country country = null;
        int n=serviceProviderList.size();
        for(int i=0;i<n;i++)
        {
            List<Country>countryList=serviceProviderList.get(i).getCountryList();
            for(int j=0;j<countryList.size();j++)
            {
                Country dummy=countryList.get(j);
               if(dummy.getCountryName().toString().equals(countryName))
               {
                   if(country==null)
                       country=dummy;
                   else if (dummy.getId()<country.getId())
                   {
                       country=dummy;
                   }
               }

            }


        }
        if(country==null)
            throw new Exception("Not possible");




        user.setConnected(true);
        Connection connection=new Connection();
        List<Connection>connectionList=user.getConnectionList();
        connectionList.add(connection);
        connection.setUser(user);



return user;


    }
    @Override
    public User disconnect(int userId) throws Exception {

        User user=userRepository2.findById(userId).get();
        if(!user.isConnected())
            throw new Exception("disconnected");
        user.setMaskedIp(null);
        user.setConnected(false);
        return  user;



    }
    @Override
    public User communicate(int senderId, int receiverId) throws Exception {
        User sender=userRepository2.findById(senderId).get();
        User receiver=userRepository2.findById(receiverId).get();
     if(receiver.isConnected())
      {
          String countrycode=receiver.getMaskedIp().substring(0,3);
          receiver.getCountry().setCountryName(CountryName.valueOf(countrycode));
      }
     if(receiver.getCountry().getCountryName().equals(sender.getCountry().getCountryName()))
     {
         return  sender;
     }

    return sender;

    }
}
