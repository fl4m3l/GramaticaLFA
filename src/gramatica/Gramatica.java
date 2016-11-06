package gramatica;

import java.util.ArrayList;
import java.util.Arrays;

public class Gramatica
{
    private ArrayList<String> _naoTerminais = new ArrayList<>();
    private ArrayList<String> _terminais = new ArrayList<>();
    private ArrayList<String> _regrasProducao = new ArrayList<>();
    private String _simboloInicial;
    private String buffer = "";
    
    public Gramatica(String naoTerminais, String terminais, String regrasProducao, String simboloInicial)
    {
        _naoTerminais.addAll(Arrays.asList(naoTerminais.split(", ")));
        _terminais.addAll(Arrays.asList(terminais.split(", ")));
        _regrasProducao.addAll(Arrays.asList(regrasProducao.split(", ")));
        _simboloInicial = simboloInicial;
    }
    
    public String getBuffer()
    {
        buffer += _simboloInicial + "\n";
        return buffer;
    }
    
    private String procurarPrimeiroTerminal(String palavra)
    {
        for (int i = 0; i < palavra.length(); i++)
        {
            String letra = palavra.substring(i, i + 1);
            if (letra.toUpperCase().equals(letra))
                return letra;
        }
        return null;
    }
    
    private boolean verificar(String caminho, String palavra)
    {
        String naoTerminal;
       
        if (caminho.length() > palavra.length())
            return false;
        if (caminho.equals(palavra))
            return true;
        naoTerminal = procurarPrimeiroTerminal(caminho);
        if (naoTerminal == null)
            return false;
        for (int i = 0; i < _regrasProducao.size(); i++)
        {
           String letraNaoTerminal = naoTerminal.substring(0, 1);
           String letraRegraProducao = _regrasProducao.get(i).substring(0, 1);
           if (letraNaoTerminal.equals(letraRegraProducao))
           {
               String novoCaminho = "";
               novoCaminho += caminho.substring(0, caminho.indexOf(naoTerminal));
               novoCaminho += _regrasProducao.get(i).substring(1, _regrasProducao.get(i).length());
               novoCaminho += caminho.substring(caminho.indexOf(naoTerminal) + 1, caminho.length());
               if (verificar(novoCaminho, palavra))
               {
                   buffer += novoCaminho + "\n";
                   return true;
               }
           }
        }
        return false;
    }
    
    public boolean fazerVerificacao(String palavra)
    {
        return verificar(_simboloInicial, palavra);
    }
}
