package com.example.bookstore.controller;

import com.example.bookstore.entity.Customer;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.OrderDetail;
import com.example.bookstore.repository.OrderDetailRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "orderList/page", method = RequestMethod.GET)
    public String getOrders(@RequestParam(defaultValue = "0") int page, Model model, UriComponentsBuilder uriBuilder) {
        Sort sort = Sort.by("id").ascending();
        PageRequest pageRequest = PageRequest.of(page, 5, sort);
        Page<Order> orderPage = orderRepository.findAll(pageRequest);

        model.addAttribute("orderPage",orderPage);

        String previousUrl = uriBuilder.path("/orderList/page").queryParam("page", page - 1).build().toUriString();
        model.addAttribute("previousUrl", previousUrl);

        String nextUrl = uriBuilder.path("/orderList/page").queryParam("page", page + 1).build().toUriString();
        model.addAttribute("nextUrl", nextUrl);

        return "order/orderList";
    }

    @RequestMapping(value = "/findOrder", method = RequestMethod.GET)
    public String FindOrder(UriComponentsBuilder uriBuilder,
                            @RequestParam("id") String id, @Param("id") String id1,
                            @RequestParam(defaultValue = "0") int page, Model model, HttpServletRequest request){

        id = request.getParameter("id");
        Pageable pageable = PageRequest.of(0, 5);

        List<Order> listOrder = orderService.searchOrders(id);
        Page<Order> pageOrder = new PageImpl<>(listOrder, pageable, listOrder.size());
        model.addAttribute("orderPage", pageOrder);

        return "order/orderList";
    }

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String saveOrder(Model model, Order order, UriComponentsBuilder uriBuilder,
                               @RequestParam(defaultValue = "0") int page) {

        if (orderRepository.findById(order.getId())  != null){
            model.addAttribute("isUpdate", "Thêm mới đơn hàng " + order.getId() + " thành công!!");
            orderRepository.save(order);
            return getOrders(page,model,uriBuilder);

        }else{
            model.addAttribute("mess", "Save order Successfully!!");
            orderRepository.save(order);
            return newPage(model);
        }
    }

    @RequestMapping(value = "/newCustomer", method = RequestMethod.GET)
    public String newPage(Model model) {
        return "order/orderNew";
    }

    @ModelAttribute("ORDER")
    public Order initOrder() {
        return new Order();
    }
    @ModelAttribute("ORDERDETAIL")
    public OrderDetail initOrderDetail() {
        return new OrderDetail();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteOrder(@PathVariable("id") String id, UriComponentsBuilder uriBuilder,
                                 @RequestParam(defaultValue = "0") int page, Model model) {
        boolean isDel = orderService.deleteOrder(id);
        if (isDel == true) {
            model.addAttribute("isOk", "Xóa đơn hàng với mã " + id + " thành công!");
        }
        return getOrders(page, model, uriBuilder);
    }

    @RequestMapping(value = "orderDetail/{id}", method = RequestMethod.GET)
    public String orderDetail(@PathVariable("id") String id, Model model) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            order.get().setTotal(order.get().calculateTotal());
        }
        model.addAttribute("order", order);

        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder(order.orElse(null));
        model.addAttribute("orderDetails", orderDetails);
        return "order/orderDetail";
    }


    @RequestMapping(value = "/newOrder", method = RequestMethod.GET)
    public String newOrder(Model model) {
        return "order/orderNew";
    }


}