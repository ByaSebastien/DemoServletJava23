package bstorm.be.demoservletjava23.repositories;

import bstorm.be.demoservletjava23.domain.entities.Author;
import bstorm.be.demoservletjava23.exceptions.EntityNotFoundException;
import bstorm.be.demoservletjava23.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRepositoryImpl extends BaseRepositoryImpl<Author> implements AuthorRepository{
    public AuthorRepositoryImpl() {
        super("AUTHOR", "AUTHOR_ID");
    }

    @Override
    protected Author buildEntity(ResultSet rs) {
        try {
            return Author.builder()
                    .id(rs.getInt("author_id"))
                    .firstname(rs.getString("firstname"))
                    .lastname(rs.getString("lastname"))
                    .pseudo(rs.getString("pseudo"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Author add(Author author) {

        try {
            Connection conn = DatabaseConnectionManager.openConnection();
            PreparedStatement psmt = conn.prepareStatement("INSERT INTO AUTHOR(FIRSTNAME,LASTNAME,PSEUDO)" +
                                                               "VALUES (?,?,?) RETURNING *");
            psmt.setString(1, author.getFirstname());
            psmt.setString(2, author.getLastname());
            psmt.setString(3, author.getPseudo());

            ResultSet rs = psmt.executeQuery();

            if(!rs.next())
                throw new EntityNotFoundException();

            Author newAuthor = buildEntity(rs);


            return newAuthor;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Integer id, Author author) {

        try {
            Connection conn = DatabaseConnectionManager.openConnection();
            PreparedStatement psmt = conn.prepareStatement("UPDATE AUTHOR " +
                                                               "SET FIRSTNAME = ?," +
                                                                   "LASTNAME = ?," +
                                                                   "PSEUDO = ? " +
                                                               "WHERE AUTHOR_ID = ?");
            psmt.setString(1, author.getFirstname());
            psmt.setString(2, author.getLastname());
            psmt.setString(3, author.getPseudo());
            psmt.setInt(4, id);

            int nbRow = psmt.executeUpdate();

            DatabaseConnectionManager.closeConnection();

            return nbRow == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
