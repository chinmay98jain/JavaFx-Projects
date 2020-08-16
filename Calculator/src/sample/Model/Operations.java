package sample.Model;

public class Operations {
    public double oprtn(long n1,long n2,String operator)
    {
        switch(operator)
        {
            case "+":
                return n1+n2;

            case "-":
                return n1-n2;

            case "x":
                return n1*n2;

            case "/":
                if(n2==0)
                    return 0;
                else
                return n1/n2;


            default:
                return 0;

        }
    }
}
