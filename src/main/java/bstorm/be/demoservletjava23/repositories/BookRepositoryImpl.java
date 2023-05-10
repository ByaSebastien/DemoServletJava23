package bstorm.be.demoservletjava23.repositories;

import bstorm.be.demoservletjava23.domain.entities.Book;
import bstorm.be.demoservletjava23.exceptions.EntityNotFoundException;
import bstorm.be.demoservletjava23.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepositoryImpl extends BaseRepositoryImpl<Book> implements BookRepository {

    public BookRepositoryImpl() {
        super("BOOK", "BOOK_ID");
    }

    @Override
    protected Book buildEntity(ResultSet rs){

        try {
            return Book.builder()
                    .id(rs.getInt("BOOK_ID"))
                    .title(rs.getString("TITLE"))
                    .description(rs.getString("DESCRIPTION"))
                    .authorId(rs.getInt("AUTHOR_ID"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book add(Book book){

        try{
            Connection conn = DatabaseConnectionManager.getConnection();
            PreparedStatement psmt = conn.prepareStatement("INSERT INTO BOOK (TITLE,DESCRIPTION,AUTHOR_ID) VALUES (?,?,?) RETURNING *");
            psmt.setString(1, book.getTitle());
            psmt.setString(2, book.getDescription());
            psmt.setInt(3,book.getAuthorId());
            ResultSet rs = psmt.executeQuery();
            if(!rs.next())
                throw new EntityNotFoundException();

            DatabaseConnectionManager.closeConnection();
            return buildEntity(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Integer id, Book book) {

        try {
            Connection conn = DatabaseConnectionManager.getConnection();
            PreparedStatement psmt = conn.prepareStatement("UPDATE BOOK SET TITLE = ?, DESCRIPTION = ?, AUTHOR_ID = ? WHERE BOOK_ID = ?");
            psmt.setString(1,book.getTitle());
            psmt.setString(2, book.getDescription());
            psmt.setInt(3,book.getAuthorId());
            psmt.setInt(4,id);

            int nbRows = psmt.executeUpdate();

            DatabaseConnectionManager.closeConnection();
            return nbRows == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
