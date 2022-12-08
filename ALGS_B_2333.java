package algs_b_2333;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.String;

/**
 *
 * @author Loukas Anastasiadis AEM : 2333
 * @mail : aploukas@csd.auth.gr
 * 
 */
public class ALGS_B_2333 {
    /**
     * 
     * @param args
     * @throws FileNotFoundException 
     * @Product is the final matrix with the minimum costs 
     * @Matrix is the input matrix.
     * @Communication is the symmetric matrix which
     * defines the cost for two different machines to transfer data to 
     * each other.
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        String path= args[0];
        Scanner file = new Scanner (new File(path));
        int procedures= file.nextInt();
        int machines=file.nextInt();
        file.nextLine();
        int [][] Matrix= new int [procedures][machines];
        int i,j;
        for(i=0;i<procedures;i++){
            for(j=0;j<machines;j++){
                Matrix[i][j]=file.nextInt();
            }
        }
        file.nextLine();
        int [][] Communication=new int [machines][machines];
        for(i=0;i<machines;i++){
            for(j=0;j<machines;j++){
                Communication[i][j]=file.nextInt();
            }
        }
        //Product is going to be the final matrix with min costs.
        int [][] Product= new int [procedures][machines];
        //inserts the first line as it is.
        for (i=0;i<machines;i++){
            Product[0][i]=Matrix[0][i];
        }
        //goes to set the other lines.
        int min,h;
        i=1;
        while(i<procedures){
            for(j=0;j<machines;j++){
                //declares that the minimum cost is the first above element.
                min=Product[i-1][j];
                /*checks if there is a smaller value than the declared minimum
                BEFORE the column of the declared minimum and 
                sets it approprietly
                */
                for(h=0;h<j;h++){
                    if (((Product[i-1][h]+Communication[j][h]))<min){
                        min=(Product[i-1][h]+Communication[j][h]);
                    }
                }
                /*checks if there is a smaller vallue AFTER the column of the
                declared minimum and sets it approprietly
                */
                for(h=j+1;h<machines;h++){
                    if((Product[i-1][h]+Communication[j][h])<min){
                        min=Product[i-1][h]+Communication[j][h];
                    }
                }
                //now that the minimum cost is known it stores it.
                Product[i][j]=min+Matrix[i][j];
                                
            }
            i++;
        }
        for(i=0;i<procedures;i++){
            for(j=0;j<machines;j++){
                System.out.print(Product[i][j]+" ");
            }
            System.out.println();
        }
    }
    
}
