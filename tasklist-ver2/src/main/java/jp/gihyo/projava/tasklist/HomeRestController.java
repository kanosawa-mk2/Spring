package jp.gihyo.projava.tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {
	
	record TaskItem(String id, String task, String deadline, boolean done) {}

	private List<TaskItem> taskItems = new ArrayList<>();

	/**
	 * <pre>
	 * タスク追加
	 * 
	 * 例：http://localhost:8080/restadd?task=Javaの勉強&deadline=2021-09-30
	 * </pre>
	 * @param task
	 * @param deadline
	 * @return
	 */
	@GetMapping("/restadd")
	String addItem(String task,String deadline) {
		String id = UUID.randomUUID().toString().substring(0,8);
		TaskItem item = new TaskItem(id, task, deadline, false);
		taskItems.add(item);
		
		return "タスクを追加しました。";
	}
	
	/**
	 * <pre>
	 * タスク一覧取得
	 * 
	 * 例：http://localhost:8080/restlist
	 * </pre>
	 * @param task
	 * @param deadline
	 * @return
	 */
	@GetMapping("/restlist")
	String listItems() {
		String result = taskItems.stream()
				.map(TaskItem::toString)
				.collect(Collectors.joining(", "));
		return result;
	}
	
	
	@GetMapping("/resthello")
	String hello() {
		return """
				Hello.
				It works!
				現在時刻は%sです。
				""".formatted(LocalDateTime.now());

	}
}
