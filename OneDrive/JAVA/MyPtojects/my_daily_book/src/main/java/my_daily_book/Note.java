package my_daily_book;

public class Note {
    private int note_id;
    private String note_title;
    private int project_id;
    private String note_date;
    private String note_time;
    private String note;
    public Note(int note_id, String note_title, int project_id, String note_date,String time, String note) {
        this.note_id = note_id;
        this.note_title = note_title;
        this.project_id = project_id;
        this.note_date = note_date;
        this.note = note;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getNote_date() {
        return note_date;
    }
    public String getNote_time() {
        return note_time;
    }

    public void setNote_date(String note_date) {
        this.note_date = note_date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
