package config;

import com.google.inject.AbstractModule;
import repository.custom.CustomerRepository;
import repository.custom.impl.CustomerRepositoryImpl;
import service.custom.CustomerService;
import service.custom.impl.CustomerServiceImpl;

public class AppModule extends AbstractModule {
    @Override
    protected void configure(){
        bind(CustomerService.class).to(CustomerServiceImpl.class);
        bind(CustomerRepository.class).to(CustomerRepositoryImpl.class);
    }
}
