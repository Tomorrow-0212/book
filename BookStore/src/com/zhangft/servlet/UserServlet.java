package com.zhangft.servlet;

import com.google.code.kaptcha.Constants;
import com.zhangft.bean.User;
import com.zhangft.service.UserService;
import com.zhangft.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserService();

    /**
     * 处理用户登录的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 使用自己编写的工具类来将请求参数封装到对象中
        User user = WebUtils.params2bean(request,new User());
        // 登录之后的结果
        User userResult = userService.login(user);
        if(userResult == null){
            // 登录失败
            request.setAttribute("message","用户名或密码错误！");
            request.setAttribute("username",user.getUsername());
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            // 登录成功
            HttpSession session = request.getSession();
            session.setAttribute("user",userResult);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }

    /**
     * 用户注销的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取到seesion对象，然后销毁实现注销功能
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    /**
     * 处理用户注册的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 使用自己编写的工具类来将请求参数封装到对象中
        User user = WebUtils.params2bean(request,new User());
        // 用户输入的验证码
        String code = request.getParameter("code");
        // session中保存的验证码
        HttpSession session =  request.getSession();
        String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 判断填写的验证码跟session中的验证码是否是相同的
        if(code != null && sessionCode != null){
            if(!code.equals(sessionCode)){
                request.setAttribute("message","验证码错误!");
                request.setAttribute("username",user.getUsername());
                request.setAttribute("email",user.getEmail());
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
                return;
            }
        }

        // 通过请求来的用户名判断数据库中是否已经存在此名称的数据
        User userBean = userService.queryByName(user);
        if (userBean == null){
            // 注册之后的结果
            boolean registResult = userService.regist(user);
            if(registResult){
                // 注册成功
                try {
                    response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                // 注册失败
                request.setAttribute("message","注册失败,请重新注册");
                request.setAttribute("username",user.getUsername());
                request.setAttribute("email",user.getEmail());
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }
        }else{
            // 因数据库以存在此数据而导致注册失败
            request.setAttribute("message","注册失败，用户名已存在");
            request.setAttribute("username",user.getUsername());
            request.setAttribute("email",user.getEmail());
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
    }
}
