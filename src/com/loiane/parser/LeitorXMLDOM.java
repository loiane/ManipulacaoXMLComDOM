package com.loiane.parser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Classe que faz o parser/leitura de um arquivo XML e imprime o conteúdo
 * do arquivo no console
 * 
 * @author Loiane Groner
 *
 */
public class LeitorXMLDOM {

	/**
	 * Realiza a leitura do arquivo XML informado como parâmetro
	 * @param arquivoXML path/nome do arquivo XML
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public void realizaLeituraXML(String arquivoXML) throws ParserConfigurationException, SAXException, IOException{
			//fazer o parse do arquivo e criar o documento XML
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(arquivoXML);

			//Passo 1: obter o elemento raiz
			Element raiz = doc.getDocumentElement();
			System.out.println("O elemento raiz é: " + raiz.getNodeName());

			//Passo 2: localizar os elementos filhos da agenda
			NodeList listaContatos = raiz.getElementsByTagName("contato");

			//Passo 3: obter os elementos de cada elemento contato
			for (int i=0; i<listaContatos.getLength(); i++){
				System.out.println();

				//como cada elemento do NodeList é um nó, precisamos fazer o cast
				Element contato = (Element) listaContatos.item(i);

				//Passo 4: obter o atributo id do contato
				Attr id = contato.getAttributeNode("id");
				System.out.println("Contato id: " + id.getNodeValue());

				//Passo 5: obtém o nome do contato
				NodeList listaNomes = contato.getElementsByTagName("nome");
				Node nome = listaNomes.item(0).getFirstChild();
				System.out.println("Nome: " + nome.getNodeValue());

				//Passo 6: obtém o endereço do contato
				NodeList listaEndereco = contato.getElementsByTagName("endereco");
				Node endereco = listaEndereco.item(0).getFirstChild();
				System.out.println("Endereço: " + endereco.getNodeValue());

				//Passo 7: obtém o telefone do contato
				NodeList listaTelefone = contato.getElementsByTagName("telefone");
				Node telefone = listaTelefone.item(0).getFirstChild();
				System.out.println("Telefone: " + telefone.getNodeValue());

				//Passo 8: obtém o email do contato
				NodeList listaEmail = contato.getElementsByTagName("email");
				Node email = listaEmail.item(0).getFirstChild();
				System.out.println("Email: " + email.getNodeValue());

			}
	}

	public static void main(String[] args){
		
		LeitorXMLDOM parser = new LeitorXMLDOM();
		try {
			parser.realizaLeituraXML("contato.xml");
			
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
