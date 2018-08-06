package io.tide.xml.encryption;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Collections;

import javax.naming.Reference;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;

import org.w3c.dom.Document;

public class XMLEncrytpionExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void generateXMLDigitalSignature(String originalXmlFilePath, String destnSignedXmlFilePath,
			String privateKeyFilePath, String publicKeyFilePath) {
		// 获取XML文档对象
		Document doc = getXmlDocument(originalXmlFilePath);

		// 创建XML签名工厂
		XMLSignatureFactory xmlSigFactory = XMLSignatureFactory.getInstance("DOM");
		PrivateKey privateKey = new KryptoUtil().getStoredPrivateKey(privateKeyFilePath);
		DOMSignContext domSignCtx = new DOMSignContext(privateKey, doc.getDocumentElement());
		Reference ref = null;
		SignedInfo signedInfo = null;
		try {
			ref = xmlSigFactory.newReference("", xmlSigFactory.newDigestMethod(DigestMethod.SHA1, null),
					Collections.singletonList(
							xmlSigFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
					null, null);
			signedInfo = xmlSigFactory.newSignedInfo(
					xmlSigFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
							(C14NMethodParameterSpec) null),
					xmlSigFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		} catch (InvalidAlgorithmParameterException ex) {
			ex.printStackTrace();
		}

		// 传入公钥路径
		KeyInfo keyInfo = getKeyInfo(xmlSigFactory, publicKeyFilePath);

		// 创建新的XML签名
		XMLSignature xmlSignature = xmlSigFactory.newXMLSignature(signedInfo, keyInfo);
		try {
			// 对文档签名
			xmlSignature.sign(domSignCtx);
		} catch (MarshalException ex) {
			ex.printStackTrace();
		} catch (XMLSignatureException ex) {
			ex.printStackTrace();
		}

		// 存储签名过的文档
		storeSignedDoc(doc, destnSignedXmlFilePath);
	}

	private Document getXmlDocument(String originalXmlFilePath) {
		// TODO Auto-generated method stub
		return null;
	}

}
