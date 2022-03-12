package ro.tuc.tp.Dao;

import ro.tuc.tp.Connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * The Class AbstractDAO.
 *@author Talpos Andreea
 * @param <T>
 *             generic type where T is a model class
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    @SuppressWarnings("unchecked")
    /**
     * Constructor - instantiates a new abstract DAO
     */
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    /**
     * Creates the select query.
     *
     * @param field
     *              the element is searched using this field
     * @return the query
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }
    /**
     * Creates the delete query.
     *
     * @param field
     *              the element is searched using this field
     * @return the query
     */
    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append(" DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }
    /**
     * Creates the update query.
     *
     * @param t
     *              the element that needs to be updated is searched using this field
     * @return the query
     */
    private String createUpdateQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        for (Field field : type.getDeclaredFields()) {
            sb.append(field.getName() + "=?,");
        }
        sb.deleteCharAt(sb.length() - 1);// delete last ,(comma)
        sb.append(" WHERE id=?");
        return sb.toString();
    }
    /**
     * Creates the insert query.
     *
     * @param t
     *              the element that needs to be inserted
     * @return the query
     */
    private String createInsertQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES (");
        for (int i = 0; i < type.getDeclaredFields().length; i++) {
            if(i!=type.getDeclaredFields().length-1)
                sb.append("?,");
            else
                sb.append("?)");
        }

        return sb.toString();
    }
    /**
     * Find all elements in a table
     *
     * @return a list with the model objects returned
     */
    public ArrayList<T> findAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = sb.toString();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * Find object in database by id.
     *
     * @param id
     *            the id to search for
     * @return  a model object having the corresponding id taken as input
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * Creates a list of object from the resultset of type 'type'
     *
     * @param resultSet
     *            the result set
     * @return the list of objects
     */
    private ArrayList<T> createObjects(ResultSet resultSet) {
        ArrayList<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * Creates a table of object from the objList of type 'type'
     *
     * @param objList
     *            the list of objects
     * @return the table
     */
    public JTable createTable(ArrayList<T> objList) {
        JTable table;
        int size = objList.getClass().getDeclaredFields().length+1;
        String[] columns = new String[size];
        String[][] content = new String [300][size];
        int i = 0;
        try{
        for (Field field : type.getDeclaredFields()) {
                columns[i] = field.getName();
                i++;
            }
        i=0;
        for(T obj : objList){
            int j=0;
            for(Field field: type.getDeclaredFields()) {
                PropertyDescriptor pr = new PropertyDescriptor(field.getName(), type);
                Method method = pr.getReadMethod();
                try {
                    content[i][j] = method.invoke(obj).toString();
                } catch (NullPointerException e) {
                    content[i][j] = null;
                }
                j++;
            }
            i++;
            }
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (IntrospectionException introspectionException) {
            introspectionException.printStackTrace();
        } catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
        }
        DefaultTableModel myTable = new DefaultTableModel(content,columns);
        table= new JTable(myTable);
        return table;
    }
    /**
     * Updates object t into the database
     *
     * @param t
     *            the model object to insert
     * @return 1 if successfull, -1 otherwise
     */
    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = -1;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            for (Field field : t.getClass().getDeclaredFields()) {
                PropertyDescriptor pr = null;
                pr=new PropertyDescriptor(field.getName(), type);
                Method m = pr.getReadMethod();
                assert statement!=null;
                statement.setObject(i, m.invoke(t));
                i++;
            }
            result = statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return result;
    }
    /**
     * Updates object t in the database with its new values
     *
     * @param t
     *            the model object to update
     * @return 1 if successfull, -1 otherwise
     */
    public int update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = -1;
        String query = createUpdateQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i = 1;
            for (Field field : t.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object update = field.get(t);
                statement.setObject(i, update);
                if (i == 1)
                    statement.setObject(t.getClass().getDeclaredFields().length + 1, update);
                i++;
            }
            result = statement.executeUpdate();
            //return createObjects(result).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return result;
    }
    /**
     * Deletes object t from the corresponding table
     *
     * @param t
     *            the model object to delete
     * @return 1 if successfull, -1 otherwise
     */
    public int delete(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = -1;
        Field field = t.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        String query = createDeleteQuery(field.getName());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, (int) field.get(t));
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return result;
    }
}

