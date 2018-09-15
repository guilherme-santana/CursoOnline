package br.com.clogos.curso.dao;

import java.util.List;

public interface GenericDAO {
	Boolean save(Object oT);
	Boolean update(Object oT);
	Boolean delete(Object oT);
	Boolean saveList(List<?> list);
	List<?> findAll(Class<?> clazz, String coluna, String order, String join);
	Object findID(Class<?> clazz, String coluna, Long id);
	Object findString(Class<?> clazz, String coluna, String param);
}
