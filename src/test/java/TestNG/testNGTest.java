package TestNG;

import com.beust.jcommander.Parameterized;
import repository.UserRepository;
import servlet.RegistrationServlet;

import org.testng.annotations.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class testNGTest {


    private static final String USER_SERVICE = "UserRepository";
    private static final String INDEX_JSP = "/registration.jsp";

    private String[][] params = {
            {"asdfasdf", "asdfasdf", "asdfasdf", "asdfasdf", "asdfasdf", "asdf@asdf.asdf", "(123)123123123", "123456"}
    };

    private static Class<ServletConfig> config;
    private static Class<ServletContext> context;
    private static Class<RequestDispatcher> rd;
    private static Class<UserRepository> userRepository;
    private static RegistrationServlet servlet;

    private HttpServletRequest request;
    private HttpServletResponse response;

    private String firstName;
    private String lastName;
    private String password;
    private String password2;
    private String mail;
    private String login;
    private String gender;

    public testNGTest(String firstName, String lastName, String login, String password1, String password2, String email, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password1;
        this.password2 = password2;
        this.mail = email;
        this.gender = gender;
    }
    @BeforeSuite
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Ivan", "Ivanov", "Ivanchik", "123", "123", "asdf@asdf.asdf", "male"},
                {"Petro", "Petrov", "Petrovich", "222", "222", "petyka@asdf.asdf", "male"}
        });
    }

    @BeforeClass
    public static void initBeforeClass( ) throws IOException, ServletException {
        userRepository = (UserRepository.class);
        servlet = new RegistrationServlet();
        config = (ServletConfig.class);
        context = (ServletContext.class);
        rd = (RequestDispatcher.class);
        when(config.getServletContext()).thenReturn(context);
        when(context.getAttribute(USER_SERVICE)).thenReturn(userRepository);
    }

    @Test(groups = { "success_path" })
    public void testWithValidData( ) throws ServletException, IOException {
        (request.getParameter("firstName")).thenReturn(params[0][FIRST_NAME]);
        when(request.getParameter("lastName")).thenReturn(params[0][LAST_NAME]);
        when(request.getParameter("authorization")).thenReturn(params[0][LOGIN]);
        when(request.getParameter("password1")).thenReturn(params[0][PASSWORD1]);
        when(request.getParameter("password2")).thenReturn(params[0][PASSWORD2]);
        when(request.getParameter("email")).thenReturn(params[0][EMAIL]);
        when(request.getParameter("phoneNumber")).thenReturn(params[0][PHONE_NUMBER]);
        when(request.getParameter("captcha")).thenReturn(params[0][CAPTCHA]);


        servlet.init(config);
        servlet.doPost(request, response);

        verify(response, times(1)).sendRedirect(INDEX_JSP);
    }

    @Test
    public void testWithInvalidData() throws ServletException, IOException {
        final List<Map<String, String>> list = new ArrayList<>();
        when(request.getParameter("firstName")).thenReturn(firstName);
        when(request.getParameter("lastName")).thenReturn(lastName);
        when(request.getParameter("authorization")).thenReturn(login);
        when(request.getParameter("password1")).thenReturn(password);
        when(request.getParameter("password2")).thenReturn(password2);
        when(request.getParameter("email")).thenReturn(mail);
        when(request.getParameter("gender")).thenReturn(gender);
        doAnswer(invocationOnMock -> {
            Object[] args = invocationOnMock.getArguments();
            list.add((Map<String, String>) args[1]);
            return null;
        }).when(request).setAttribute(eq("errors"), any());


        assumeTrue(list.isEmpty());
        servlet.init(config);
        servlet.doPost(request, response);

        assertEquals(errorsCount, list.get(0).size());
    }
}

