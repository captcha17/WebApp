package by.rakushev.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;


import by.rakushev.entity.Project;
import by.rakushev.entity.Task;
import by.rakushev.entity.User;
import by.rakushev.exception.DalException;
import by.rakushev.entity.FileUploadForm;
import by.rakushev.dal.CrudDao;

@Controller
@RequestMapping("/user/")
public class MainController {
	private Logger LOGGER = Logger.getLogger(MainController.class.getSimpleName());

	@Autowired
	private CrudDao crudDao;

	@RequestMapping("/login")
	public String login(HttpSession session) {
		if (session != null) {
			session.removeAttribute("login");
		}
		session.invalidate();
		return "login";
	}

	@RequestMapping("/listMain")
	public String listMain(Model model) {
		try {
			List<User> list = crudDao.list(User.class);
			model.addAttribute("listUsers", list);
			List<Project> listProject = reverse(crudDao.list(Project.class));
			model.addAttribute("listProject", listProject);
		} catch (DalException e) {
			LOGGER.severe(e.getMessage());
		}
		return "list";
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		String login = request.getParameter("login");
		String pass = request.getParameter("password");

		if (login.equalsIgnoreCase("manager") && pass.equalsIgnoreCase("manager")) {

			return "redirect:/user/listMain";
		}
		return "error";
	}

	@RequestMapping("/addUser")
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isValid = false;
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if ((login != null && login.length() != 0) && (password != null && password.length() != 0)) {
			isValid = true;
			try {
				User user = new User();
				user.setLogin(login);
				user.setPassword(password);
				crudDao.merge(user);
			} catch (DalException e) {
				LOGGER.severe(e.getMessage());
			}
		}
		map.put("isValid", isValid);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(new Gson().toJson(map));

	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request,Model model) {
		String developer = request.getParameter("developer");
		try {
			crudDao.delete(User.class, developer);
		} catch (DalException e) {
			LOGGER.severe(e.getMessage());
		}
		return "redirect:/user/listMain";
	}

	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
	public String add(Model model, @ModelAttribute Project project,
			@ModelAttribute("uploadForm") FileUploadForm uploadForm) {
		List<MultipartFile> files = null;
		MultipartFile file = null;
		byte[] image = new byte[1024 * 1024 * 100];
		try {
			files = uploadForm.getFiles();
			file = files.get(0);
			image = file.getBytes();
			project.setImage(image);
			crudDao.merge(project);

		} catch (DalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/user/listMain";
	}
	
	@RequestMapping("/addTask")
	public String change(@RequestParam("index") Long id,HttpServletRequest req,Model model) {
		Task newTask;
		try {
			String task=(String)req.getParameter("task");
			if (!task.isEmpty()){
				newTask=new Task();
			newTask.setNameTask(task);
			newTask.setProject_id(id);
			crudDao.merge(newTask);
			List<User> list = crudDao.list(User.class);
			model.addAttribute("listUsers", list);
			Project project = crudDao.check(Project.class, id);
			List<Task> listTask = reverse(crudDao.getGroup(Task.class, id));
			model.addAttribute("projectUnique", project);
			model.addAttribute("listTask", listTask);
			}
		} catch (DalException e) {
			LOGGER.severe(e.getMessage());
		}
		return "project";
	}

	@RequestMapping("/image")
	public String getImage(@RequestParam("index") Long id, HttpServletResponse resp) {
		try {
			Project projImage = crudDao.check(Project.class, id);
			resp.setContentType("image/jpeg");
			OutputStream out = resp.getOutputStream();
			resp.setContentLength(projImage.getImage().length);
			out.write(projImage.getImage());
		} catch (DalException e) {
			LOGGER.severe(e.getMessage());
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}
		return "list";
	}
	
	@RequestMapping("/project")
	public String getProject(@RequestParam("index") Long id, Model model) {
		try {
			List<User> list = crudDao.list(User.class);
			model.addAttribute("listUsers", list);
			Project project = crudDao.check(Project.class, id);
			List<Task> listTask = reverse(crudDao.getGroup(Task.class, id));
			model.addAttribute("projectUnique", project);
			model.addAttribute("listTask", listTask);
		} catch (DalException e) {
			LOGGER.severe(e.getMessage());
		}
		return "project";
	}

	public static <T> List<T> reverse(List<T> src) {
		List<T> results = new ArrayList<T>(src);
		Collections.reverse(results);
		return results;
	}

	// public boolean check(String login, String pass) {
	// try {
	// List<Clients> clients = crudDao.list(Clients.class);
	// for (Clients cl : clients) {
	// if (cl.getLogin().equalsIgnoreCase(login) &&
	// cl.getPassword().equalsIgnoreCase(pass)) {
	// return true;
	// }
	// }
	// } catch (DalException e) {
//
	// e.printStackTrace();
	// }
	// return false;
	// }
	//
	@ModelAttribute("project")
	public Project newProject() {
		return new Project();
	}
}
