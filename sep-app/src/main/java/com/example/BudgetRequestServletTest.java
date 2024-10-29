import com.example.BudgetRequestServlet;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class BudgetRequestServletTest {
    private BudgetRequestServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Before
    public void setUp() {
        servlet = new BudgetRequestServlet();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
    }

    @Test
    public void testDoGetWithValidRole() throws ServletException, IOException {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("sm");

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(request, times(1)).getRequestDispatcher("budgetRequest.jsp");
        verify(request, VerificationModeFactory.times(1)).getRequestDispatcher(anyString());
    }

    @Test
    public void testDoGetWithInvalidRole() throws ServletException, IOException {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("user");

        // Act
        servlet.doGet(request, response);

        // Assert
        verify(response, times(1)).sendRedirect("menu.jsp");
    }

    @Test
    public void testDoPostWithValidRole() throws ServletException, IOException {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("pm");
        when(request.getParameter("projectName")).thenReturn("Test Project");
        when(request.getParameter("currentBudget")).thenReturn("100000.00");
        when(request.getParameter("requestedAmount")).thenReturn("20000.00");
        when(request.getParameter("justification")).thenReturn("Additional resources needed");

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(response, times(1)).sendRedirect(anyString());
    }

    @Test
    public void testDoPostWithInvalidRole() throws ServletException, IOException {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn("user");

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(response, times(1)).sendRedirect("menu.jsp");
    }
}