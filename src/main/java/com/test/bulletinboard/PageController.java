package com.test.bulletinboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping(path="/home") 
public class PageController {
    @Autowired
    private MessageRepository messageRepository;

	@GetMapping("")
	public String home( @RequestParam(defaultValue = "0") int page,
						@RequestParam(defaultValue = "10") int size,
						@RequestParam(defaultValue = "Guest") String userID,
						Model model) {
		  // 取得所有資料並轉換為 List
		  List<Message> allMessages = new ArrayList<>();
		  messageRepository.findAll().forEach(allMessages::add);
	  
		  // 手動分頁
		  int start = page * size;
		  int end = Math.min(start + size, allMessages.size());
		  List<Message> paginatedMessages = allMessages.subList(start, end);
	  
		  // 計算分頁資訊
		  int totalPages = (int) Math.ceil((double) allMessages.size() / size);
		  if (totalPages <= 0)
			totalPages ++;

		  // 傳遞到前端的資料
		  model.addAttribute("messages", paginatedMessages);
		  model.addAttribute("currentPage", page);
		  model.addAttribute("totalPages", totalPages);
		  model.addAttribute("size", size);
		  model.addAttribute("userID", userID);
		
		return "home";
	}
	@GetMapping("/add")
	public String redirectToNextPage(@RequestParam(defaultValue = "Guest") String userID, Model model) {
		model.addAttribute("userID", userID);
		return "add";
	}
    @GetMapping("/edit")
	public String edit(@RequestParam(value = "id", required=true) Integer id, Model model) {
		Message n = messageRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Message not found with id: " + id));
		model.addAttribute("message", n);
		return "edit";
	}
	@GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Integer id) {
        Message fileEntity = messageRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Message not found with id: " + id));
        
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, 
                "attachment; filename=\"" + fileEntity.getFileName() + "\"")
            .header(HttpHeaders.CONTENT_TYPE, fileEntity.getFileContentType())
            .body(fileEntity.getFileContent());
    }
	@GetMapping("/login")
	public String login(@RequestParam(value = "id", required=false) Integer id, Model model){
		return "login";
	}
	@GetMapping("/view")
	public String view(@RequestParam(value = "id", required=false) Integer id, Model model){
		Message n = messageRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Message not found with id: " + id));
		model.addAttribute("message",n);
		return "view";
	}
}