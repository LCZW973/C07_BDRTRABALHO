package br.faculdade;
import br.faculdade.models.utils.acesso.Acesso;

import java.util.Scanner;
public class Main
{
    public  static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        Acesso.iniciarLogin();
        sc.close();
    }
}