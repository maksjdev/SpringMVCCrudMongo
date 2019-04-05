package com.springmvc.springmongodbweb.controllers;


import com.springmvc.springmongodbweb.models.Provider;
import com.springmvc.springmongodbweb.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProviderController {
    
    @Autowired
    ProviderRepository providerRepository;


    @RequestMapping("/provider")
    public String product(Model model) {
        model.addAttribute("providers", providerRepository.findAll());
        return "provider";
    }

    @RequestMapping("/createProvider")
    public String create(Model model) {
        return "createProvider";
    }

    @RequestMapping("/saveProvider")
    public String save(@RequestParam String Name, @RequestParam String prodName) {
        Provider provider = new Provider();
        provider.setName(Name);
        provider.setProductName(prodName);
        providerRepository.save(provider);
        return "redirect:/showProvider/" + provider.getId();
    }

    @RequestMapping("/showProvider/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("provider", providerRepository.findOne(id));
        return "showProvider";
    }

    @RequestMapping("/deleteProvider")
    public String delete(@RequestParam String id) {
        Provider provider = providerRepository.findOne(id);
        providerRepository.delete(provider);

        return "redirect:/provider";
    }

    @RequestMapping("/editProvider/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("provider", providerRepository.findOne(id));
        return "editProvider";
    }

    @RequestMapping("/updateProvider")
    public String update(@RequestParam String id, @RequestParam String Name, @RequestParam String prodName) {
        Provider provider = providerRepository.findOne(id);
        provider.setName(Name);
        provider.setProductName(prodName);
        providerRepository.save(provider);
        return "redirect:/showProvider/" + provider.getId();
    }
}