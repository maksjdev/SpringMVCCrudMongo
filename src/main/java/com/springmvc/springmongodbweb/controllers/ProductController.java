
package com.springmvc.springmongodbweb.controllers;

import com.springmvc.springmongodbweb.models.Product;
import com.springmvc.springmongodbweb.models.Provider;
import com.springmvc.springmongodbweb.repositories.ProductRepository;
import com.springmvc.springmongodbweb.repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;


@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProviderRepository providerRepository;

    @RequestMapping("/product")
    public String product(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String save(@RequestParam String prodName, @RequestParam String prodDesc, @RequestParam Double prodPrice, @RequestParam String prodImage) {
        Product product = new Product();
        product.setProdName(prodName);
        product.setProdDesc(prodDesc);
        product.setProdPrice(prodPrice);
        product.setProdImage(prodImage);
        List<Provider> providerList = providerRepository.findByName(prodName);
        System.out.println(providerList);
        product.setProviderList(providerList);
        System.out.println(product.toString());
        productRepository.save(product);

        return "redirect:/show/" + product.getId();
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("product", productRepository.findOne(id));
        return "show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        Product product = productRepository.findOne(id);
        productRepository.delete(product);

        return "redirect:/product";
    }
    
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("product", productRepository.findOne(id));
        return "edit";
    }
    
    @RequestMapping("/update")
    public String update(@RequestParam String id, @RequestParam String prodName, @RequestParam String prodDesc, @RequestParam Double prodPrice, @RequestParam String prodImage) {
        Product product = productRepository.findOne(id);
        product.setProdName(prodName);
        product.setProdDesc(prodDesc);
        product.setProdPrice(prodPrice);
        product.setProdImage(prodImage);
        productRepository.save(product);

        return "redirect:/show/" + product.getId();
    }
}
