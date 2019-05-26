package ohcheese.model.helper;

import javafx.scene.control.Button;
import ohcheese.controller.GeneralWindowControl;
import ohcheese.model.Address;

import java.io.IOException;

public class Job_position_Info extends GeneralWindowControl {

    private  int position_ID;
    private String job_position_name;

    private Button edit_btn;

    public static boolean class_type;
    public static int temp_id;

    public Job_position_Info(int position_ID, String job_position_name) {
        this.position_ID = position_ID;
        this.job_position_name = job_position_name;
        this.class_type = false;
        this.edit_btn = new Button("Edit");
        this.edit_btn.setOnAction(e->{
            this.temp_id = this.position_ID;
            this.class_type = true;
            try {
                openscene(e, "edit_jobposition","GeneralWindowStyle", "Admin/tools","Global_Resources");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        this.edit_btn.getStyleClass().add("edit_btn");

    }

    public int getPosition_ID() { return position_ID; }
    public void setPosition_ID(int position_ID) { this.position_ID = position_ID; }

    public String getJob_position_name() { return job_position_name; }
    public void setJob_position_name(String job_position_name) { this.job_position_name = job_position_name; }

    public Button getEdit_btn() { return edit_btn; }
    public void setEdit_btn(Button edit_btn) { this.edit_btn = edit_btn; }

    public static boolean isClass_type() { return class_type; }
    public static void setClass_type(boolean class_type) { Job_position_Info.class_type = class_type; }

    public static int getTemp_id() { return temp_id; }
    public static void setTemp_id(int temp_id) { Job_position_Info.temp_id = temp_id; }
}
