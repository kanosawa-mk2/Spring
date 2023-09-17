package jp.gihyo.projava.tasklist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	record TaskItem(String id, String task, String deadline, boolean done) {}

	//private List<TaskItem> taskItems = new ArrayList<>();
	private final TaskListDao dao;
	
	@Autowired
	public HomeController(TaskListDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/hello")
	String hello(Model model) {
		
		model.addAttribute("time",LocalDateTime.now());
		
		return "hello";// htmlのテンプレート名
	}
	
	@GetMapping("/list")
	String listItems(Model model) {
		List<TaskItem> taskItems = dao.findAll(); 
		model.addAttribute("taskList",taskItems);
		
		return "home";// htmlのテンプレート名
	}
	
	@GetMapping("/add")
	String addItem(String task,String deadline) {
		String id = UUID.randomUUID().toString().substring(0,8);
		TaskItem item = new TaskItem(id, task, deadline, false);
		dao.add(item);
		
		return "redirect:/list";//リダイレクト設定
	}
	
	@GetMapping("/delete")
	String deleteItem(String id) {
		dao.delete(id);
		return  "redirect:/list";//リダイレクト設定
	}
	
	@GetMapping("/update")
	String updateItem(String id,String task,String deadline,boolean done) {
		TaskItem taskItem = new TaskItem(id, task, deadline, done);
		dao.update(taskItem);
		return  "redirect:/list";//リダイレクト設定
	}
}
