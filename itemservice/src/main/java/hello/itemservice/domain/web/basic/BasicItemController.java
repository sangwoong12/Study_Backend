package hello.itemservice.domain.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor//
public class BasicItemController {

    private final ItemRepository itemRepository;

    /*
    public BasicItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }
    */

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);//model을 통해 데이터 view로 전송
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    private String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    private String addForm(){
        return "basic/addForm";
    }

    //@PostMapping("/add")
    private String addItemV1(@RequestParam String itemName,
                        @RequestParam int price,
                        @RequestParam Integer quantity,
                        Model model){
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    //@PostMapping("/add")
    private String addItemV2(@ModelAttribute("item") Item item//, Model model){
    ){
        itemRepository.save(item);

        //model.addAttribute("item", item); 자동 추가, 생략 가능

        return "basic/item";
    }

    //@PostMapping("/add")
    private String addItemV3(@ModelAttribute Item item){

        itemRepository.save(item);

        //model.addAttribute("item", item); 자동 추가, 생략 가능

        return "basic/item";
    }

    //PostMapping("/add")
    private String addItemV4(Item item){
        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    private String addItemV5(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getId();
    }

    @PostMapping("/add")
    private String addItemV6(Item item, RedirectAttributes redirectAttributes){
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/basic/items/{itemId}";
    }
    @GetMapping("/delete")
    private String deleteItem(){
        itemRepository.clearStore();
        return "basic /delete";
    }


    @GetMapping("{itemId}/edit")
    private String editForm(@PathVariable Long itemId,Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */

    @PostConstruct// WAS 가 뜰 때 bean이 생성된 다음 딱 한번만 실행된다.
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));

    }
}
