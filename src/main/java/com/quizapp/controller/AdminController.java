package com.quizapp.controller;

import com.quizapp.model.Contact;
import com.quizapp.model.QuizResultSummary;
import com.quizapp.model.User;
import com.quizapp.model.Question;
import com.quizapp.model.QuestionCategory;
import com.quizapp.service.QuizResultSummaryService;
import com.quizapp.service.UserService;
import com.quizapp.service.ContactService;
import com.quizapp.service.QuestionService;
import com.quizapp.service.QuestionCategoryService;


import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final QuizResultSummaryService quizResultSummaryService;

    private final QuestionService questionService;

    private final ContactService contactService;

    private final QuestionCategoryService questionCategoryService;



    public AdminController(UserService userService,QuizResultSummaryService quizResultSummaryService,ContactService contactService,QuestionService questionService,QuestionCategoryService questionCategoryService) {
        this.userService = userService;
        this.quizResultSummaryService = quizResultSummaryService;
        this.contactService = contactService;
        this.questionService = questionService;
        this.questionCategoryService = questionCategoryService;
    }

    // ✅ 确保管理员访问权限
    private boolean checkAdminAccess(HttpSession session) {
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        return isAdmin != null && isAdmin;
    }

    @GetMapping("/users")
    public String showUserManagementPage(Model model, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/user/login";

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "adminUsers";
    }



    @GetMapping("/questions")
    public String showQuestionManagementPage(@RequestParam(required = false) Integer categoryId, Model model, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/user/login";

        List<Question> questions;
        if (categoryId != null) {
            questions = questionService.getQuestionsByCategory(categoryId);
        } else {
            questions = questionService.getAllQuestions();
        }

        model.addAttribute("questions", questions);
        return "adminQuestions";
    }


    @GetMapping("/add-question")
    public String showAddQuestionPage(Model model, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/user/login";

        List<QuestionCategory> categories = questionCategoryService.getAllCategories();  // 获取所有分类
        model.addAttribute("categories", categories);
        model.addAttribute("question", new Question());  // 绑定空问题对象
        return "adminAddQuestion";  // 显示 JSP
    }

    // ✅ 处理表单提交
    @PostMapping("/add-question")
    public String addQuestion(@ModelAttribute Question question, @RequestParam("categoryId") int categoryId, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/user/login";

        // 设置问题类别
        QuestionCategory category = questionCategoryService.getCategoryById(categoryId);
        question.setCategory(category);

        // 保存问题
        questionService.saveQuestion(question);

        return "redirect:/admin/questions";  // 返回问题管理页面
    }

    // ✅ 处理删除 Question
    @GetMapping("/delete-question/{id}")
    public String deleteQuestion(@PathVariable("id") int id, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/user/login";

        questionService.deleteQuestionById(id);
        return "redirect:/admin/questions";
    }



    @GetMapping("/contact")
    public String showContactManagementPage(Model model, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/user/login";

        List<Contact> messages = contactService.getAllMessages();
        System.out.println("Fetched Messages: " + messages); // 添加日志，检查是否查询到数据

        model.addAttribute("messages", messages);
        return "adminContact";
    }

    @GetMapping("/delete-contact/{id}")
    public String deleteMessage(@PathVariable("id") int messageId, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/user/login";

        contactService.deleteMessageById(messageId); // 调用 Service 层删除消息
        return "redirect:/admin/contact";
    }


    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") int userId, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/user/login";

        userService.deleteUserById(userId);
        return "redirect:/admin/users";  //  删除后刷新页面
    }

    // ✅ 进入编辑用户页面
    @GetMapping("/edit-user/{id}")
    public String editUserPage(@PathVariable("id") int userId, Model model, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/user/login";

        User user = userService.getUserById(userId);
        model.addAttribute("user", user);
        return "editUser";  //  显示 `editUser.jsp`
    }

    // ✅ 处理编辑用户的请求
    @PostMapping("/edit-user")
    public String updateUser(@ModelAttribute User user, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/user/login";

        userService.updateUser(user);
        return "redirect:/admin/users";  //  修改后刷新用户列表
    }

    @GetMapping("/quiz-results")
    public String showQuizResultManagementPage(Model model, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/user/login";

        List<QuizResultSummary> quizResults = quizResultSummaryService.getAllQuizResults();
        model.addAttribute("quizResults", quizResults);
        return "adminQuizResults";
    }

    @GetMapping("/delete-quiz-result/{quizId}/{userId}")
    public String deleteQuizResult(@PathVariable("quizId") int quizId, @PathVariable("userId") int userId, HttpSession session) {
        if (!checkAdminAccess(session)) return "redirect:/user/login";

        quizResultSummaryService.deleteQuizResult(quizId, userId);
        return "redirect:/admin/quiz-results";
    }







}
