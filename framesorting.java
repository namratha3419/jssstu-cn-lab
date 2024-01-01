import java.util.Scanner;

class Frame {
    int frameno;
    String data;
}

public class FrameSort {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n;
        System.out.println("Enter the number of frames:");
        n = in.nextInt();
        Frame frames[] = new Frame[n];
        System.out.println("Enter frame no and data:");
        for (int i = 0; i < n; i++) {
            frames[i] = new Frame(); // Initialize each frame object
            System.out.println("Frame no:");
            frames[i].frameno = in.nextInt();
            System.out.println("Frame data:");
            frames[i].data = in.next();
        }

        int flag = 0;
        Frame temp = new Frame();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (frames[j].frameno > frames[j + 1].frameno) {
                    flag = 1;
                    temp = frames[j];
                    frames[j] = frames[j + 1];
                    frames[j + 1] = temp;
                }
            }
        }

        // Check if any swaps were made during sorting
        if (flag == 1) {
            System.out.println("The sorted frames are:");
            
             for (Frame f : frames) {
                System.out.print(" " + f.frameno);
            }
            System.out.println();
            for (Frame f : frames) {
                System.out.print(f.data);
            }
        } 
        else {
            System.out.println("Frames are already sorted.");
        }
    }
}
