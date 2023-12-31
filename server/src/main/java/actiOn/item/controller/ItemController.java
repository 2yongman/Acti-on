package actiOn.item.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import actiOn.item.dto.ItemDto;
import actiOn.item.service.ItemService;
import actiOn.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{store-id}") // 그 스토어, 그 날짜에 티켓 상태 조회
    public ResponseEntity getStoreByDate(@PathVariable("store-id") @Positive long storeId,
                                         @RequestParam("date") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate date){

        List<ItemDto> items = itemService.findItemsByStoreIdAndDate(storeId,date);
        if (items != null) {
            return new ResponseEntity<>(items, HttpStatus.OK);
        }
        return new ResponseEntity<>("예약정보를 불러오는데 실패했습니다.", HttpStatus.NOT_FOUND);
    }
}
