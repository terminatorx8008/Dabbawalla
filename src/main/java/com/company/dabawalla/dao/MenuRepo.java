package com.company.dabawalla.dao;

import com.company.dabawalla.entities.Menu;
import com.company.dabawalla.entities.Mess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepo extends JpaRepository<Menu, Integer> {
    @Query("SELECT m FROM Menu m JOIN m.mess mess WHERE mess.messId = :messId")
    public List<Menu> findByMessId(int messId);
//    select all menu
    @Query("SELECT m FROM Menu m")
    public List<Menu> selectAllMenu();

    public Menu findByMenuId(int menuId);
    public Menu findByMess(Mess mess);
}
