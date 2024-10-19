package serviceImpl;

import model.entity.Announcement;
import exception.MyException;
import model.enums.Position;
import service.AnnouncementService;
import service.CommonService;
import util.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementServiceImpl implements AnnouncementService, CommonService<Announcement> {

    static ArrayList<Announcement> announcementlist = new ArrayList<>();

    static {
        announcementlist.add(new Announcement(1, "Telefon satılır, yaxşı vəziyyətdə", 300, "Telefon", Position.PREMIUM));
        announcementlist.add(new Announcement(2, "Ev kirayə verilir, mərkəzdə", 500, "Ev Kirayə", Position.ARCHIVED));
        announcementlist.add(new Announcement(3, "İş elanları, təcrübəli proqramçı axtarılır", 0, "İş Elanı", Position.STANDARD));
        announcementlist.add(new Announcement(4, "Mebel satılır, yeni kimi", 200, "Mebel", Position.STANDARD));
    }


    @Override
    public List<Announcement> getAllPrice(int Price) {
        return announcementlist.stream().filter(a -> a.getPrice() == Price).toList();
    }

    @Override
    public List<Announcement> getAllTitle(String title) {
        return announcementlist.stream().filter(a -> a.getTitle() == title).toList();
    }

    @Override
    public void UpdateTitle(int id, String title) {
        String note = "";
        try {
            for (Announcement a : announcementlist) {
                if (a.getUserId() == id) {
                    a.setTitle(title);
                    note=a.getDescription();
                }
            }
        } catch (Exception ex) {
            throw new MyException("Don't exist this anons");
        }

        FileWriter.writeFile("UpdatedAnons.txt",note);

    }

    @Override
    public void UpdatePrice(int id, int Price) {

        String note="";
        try {
            for (Announcement a : announcementlist) {
                if (a.getUserId() == id) {
                    a.setPrice(Price);
                    note=a.getDescription();
                }
            }
        } catch (Exception ex) {
            throw new MyException("Don't exist this anons");
        }
        FileWriter.writeFile("UpdatedAnons.txt",note);
    }

    @Override
    public void add(Announcement object) {
        announcementlist.add(object);
        FileWriter.writeFile("UpdatedAnons.txt",object.getDescription());
    }

    @Override
    public List<Announcement> getAll() {
        return announcementlist;
    }

    @Override
    public Announcement getById(Integer id) {
        try {
            return announcementlist.stream().filter(a -> a.getUserId() == id).toList().get(0);
        } catch (Exception ex) {
            throw new MyException("Don't exist this anons");
        }
    }

    @Override
    public void updateById(Integer id, String name) {
        try {
            for (Announcement a : announcementlist) {
                if (a.getUserId() == id) {
                    a.setDescription(name);
                }
            }
        } catch (Exception ex) {
            throw new MyException("Don't exist this anons");
        }

        FileWriter.writeFile("UpdatedAnons.txt",name);
    }

    @Override
    public void deleteById(Integer id) {
        try {
            announcementlist.remove(id - 1);
        } catch (Exception ex) {
            throw new MyException("Don't exist this anons");
        }
        FileWriter.writeFile("DeletedAnons.txt",announcementlist.get(id-1).getDescription());
    }
}
