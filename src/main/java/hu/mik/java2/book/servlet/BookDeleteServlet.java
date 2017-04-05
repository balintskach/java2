package hu.mik.java2.book.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import hu.mik.java2.book.bean.Book;
import hu.mik.java2.service.BookService;
import hu.mik.java2.service.ServiceUtils;

@WebServlet(urlPatterns = "/book_delete")
public class BookDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BookService bookService = ServiceUtils.getBookService();
		Book book = new Book();

		if (req.getParameter("bookId") != null) {
			Integer bookId = new Integer(req.getParameter("bookId"));
			book = bookService.getBookById(bookId);
			bookService.deleteBook(book);
		}

		req.setAttribute("book", book);

		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/book_delete.jsp");

		requestDispatcher.forward(req, resp);
	}
}
