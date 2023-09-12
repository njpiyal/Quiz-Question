import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Swing_Quiz {

    private JFrame frame;
    private JPanel panel;

    private JLabel questionLabel;

    private JRadioButton option01;
    private JRadioButton option02;
    private JRadioButton option03;
    private JButton next_Button;
    private JButton finish_Button;
    private ButtonGroup options;

    private int score=0;
    private int index=0;

    private ArrayList<Question> questions;


    public Swing_Quiz() {
        frame = new JFrame("Swing Quiz ");
        panel = new JPanel();
        questionLabel = new JLabel();
        option01 = new JRadioButton();
        option02 = new JRadioButton();
        option03 = new JRadioButton();
        next_Button = new JButton("Next");
        finish_Button = new JButton("Finish");
       options = new ButtonGroup();
       questions = new ArrayList<>();
       populatingQuestions();

       set_UI();

    }

    private void populatingQuestions(){
        questions.add(new Question("What is 60 + 2?", "34","40","70","62"));
        questions.add(new Question("Name the place known as the Roof of the World?", "Amazon", "Tibet", "Mangrove Forest","Rain Forest"));
        questions.add(new Question("Name the longest river on the Earth"," Padma","Tigris","Ganga","Nile"));
        questions.add(new Question("Name the Smallest continent"," Africa","Japan"," Australia","Bangladesh"));
        questions.add(new Question("Name the planet close to the Earth"," Moon"," Mercury","Jupiter","Sun"));

    }
    private void set_UI(){
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);


        panel.setLayout(new GridLayout(6,1));
        options.add(option01);
        options.add(option02);
        options.add(option03);


    }
    private void setupUI() {
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(new GridLayout(6, 1));
        options.add(option01);
        options.add(option02);
        options.add(option03);

        panel.add(questionLabel);
        panel.add(option01);
        panel.add(option02);
        panel.add(option03);
        panel.add(next_Button);
        panel.add(finish_Button);

        finish_Button.setVisible(false);
        next_Button.addActionListener(new NextButtonListener());
        finish_Button.addActionListener(new FinishButtonListener());
        loadQuestion();
        frame.setVisible(true);
    }

    private void loadQuestion(){
        if (index< questions.size()){
            JRadioButton q = questions.get(index);
            questionLabel.setText(q.getQuestion());
            option01.setText(q.getOption01());
            option02.setText(q.getOption02());
            option03.setText(q.getOption03());
            options.clearSelection();
        }
    }
   private class NextButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String selectedOption = null;
            if (option01.isSelected())
                selectedOption = option01.getText();
            if (option02.isSelected())
                selectedOption = option02.getText();
            if (option03.isSelected())
                selectedOption = option03.getText();

            if (selectedOption != null && selectedOption.equals(questions.get(index).getCorrectAnswer())){
                score++;
            }
            index++;

            if (index < questions.size()){
                loadQuestion();
            }else {
                next_Button.setVisible(false);
                finish_Button.setVisible(true);
            }
        }
   }
   abstract private class FinishButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(frame,"Your score is : "+score);
            frame.dispose();
        }
   }

   public static void main(String[] args){
        new Swing_Quiz();
   }

}
