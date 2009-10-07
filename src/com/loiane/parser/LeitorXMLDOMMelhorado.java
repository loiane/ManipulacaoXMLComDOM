package com.loiane.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.loiane.agenda.Contato;

/**
 * Classe que faz o parser/leitura de um arquivo XML criando objetos
 * do tipo Contato
 * 
 * @author Loiane Groner
 *
 */
public class LeitorXMLDOMMelhorado {

	/**
	 * Realiza a leitura do arquivo XML informado como parâmetro
	 * @param arquivoXML path/nome do arquivo XML
	 * @return lista de contatos contidos no arquivo XML
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public List<Contato> realizaLeituraXML(String arquivoXML) throws ParserConfigurationException, SAXException, IOException{
		//fazer o parse do arquivo e criar o documento XML
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(arquivoXML);

		//Passo 1: obter o elemento raiz
		Element raiz = doc.getDocumentElement();
		System.out.println("O elemento raiz é: " + raiz.getNodeName());

		//Passo 2: localizar os elementos filhos da agenda
		NodeList listaContatos = raiz.getElementsByTagName("contato");

		List<Contato> lista = new ArrayList<Contato>(listaContatos.getLength());

		//Passo 3: obter os elementos de cada elemento contato
		for (int i=0; i<listaContatos.getLength(); i++){

			//como cada elemento do NodeList é um nó, precisamos fazer o cast
			Element elementoContato = (Element) listaContatos.item(i);

			//cria um objeto Contato com as informações do elemento contato
			Contato contato = criaContato(elementoContato);
			lista.add(contato);
		}

		return lista;
	}

	/**
	 * Obtém o valor do Text Node de um determinado elemento (se este 
	 * possuir um valor)
	 * @param elemento objeto que deseja-se obter o valor
	 * @param nomeElemento nome da tag cujo valor deseja-se obter
	 * @return valor da tag/elemento se esta existir ou null caso não exista
	 */
	public String obterValorElemento(Element elemento, String nomeElemento){
		//obtém a lista de elementos
		NodeList listaElemento = elemento.getElementsByTagName(nomeElemento);
		if (listaElemento == null){
			return null;
		}
		//obtém o elemento
		Element noElemento = (Element) listaElemento.item(0);
		if (noElemento == null){
			return null;
		}
		//obtém o nó com a informação
		Node no = noElemento.getFirstChild();
		return no.getNodeValue();
	}

	/**
	 * Cria um objeto Contato a partir das informações obtidas
	 * no arquivo XML
	 * @param elemento que se dejesa extrair o conteúdo
	 * @return Contato
	 */
	public Contato criaContato(Element elemento){
		Contato contato = new Contato();
		contato.setId(Integer.parseInt(elemento.getAttributeNode("id").getNodeValue()));
		contato.setNome(obterValorElemento(elemento,"nome"));
		contato.setEndereco(obterValorElemento(elemento,"endereco"));
		contato.setTelefone(obterValorElemento(elemento,"telefone"));
		contato.setEmail(obterValorElemento(elemento,"email"));
		return contato;
	}

	public static void main(String[] args){
		LeitorXMLDOMMelhorado parser = new LeitorXMLDOMMelhorado();
		try{
			List<Contato> contatos = parser.realizaLeituraXML("contato.xml");
			for (Contato contato : contatos){
				System.out.println(contato);
			}
		} catch (ParserConfigurationException e) {
			System.out.println("O parser não foi configurado corretamente.");
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("Problema ao fazer o parse do arquivo.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("O arquivo não pode ser lido.");
			e.printStackTrace();
		}
	}

}
