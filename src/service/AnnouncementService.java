package service;

import model.entity.Announcement;

import java.util.List;

public interface AnnouncementService {

    List<Announcement> getAllPrice(int Price);

    List<Announcement> getAllTitle(String title);

    void UpdateTitle(int id, String title);

    void UpdatePrice(int id, int Price);

}
