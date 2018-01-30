/**
 * Copyright 2014, Staples, Inc
 * All Rights Reserved
 * Author				:	Staples
 * Project ID			:	STA-0124
 * Project Name			:	Platform B - Rewards Integration
 * FileName				:	EmailUtil.java
 * Date of Creation		:	05-FEB-2014
 * Description			:	This class is used to send email to the customers
 * Version No			:	1.0
 *
 * Modification History	:
 * Date				Version No	Modified By		Description
 * 05-Feb-2014		0.1			NTT DATA		Created the controller
 * 14-Apr-2014		1.0			NTT DATA		SQA Reviewed version
 */
package com.staples.dashboard.app.utilities;

import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.staples.dashboard.app.utilities.exception.ServiceException;

/**
 * The Class EmailUtil.
 *
 * @author KumBi002
 * @version 1.0
 *  Revision history
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 *  <p>
 * <table>
 * <tr>
 * 	<td>Version</td><td>Date</td><td>Author</td><td>Description</td>
 * </tr>
 * <tr>
 *  <td>1.0</td><td>Dec 4, 2015</td><td>KumBi002</td><td>Initial Draft</td>
 * </tr>
 * </table>
 *  </p>
 *  <p>
 *  ------------------------------------------------------------
 *  </p>
 */
@Component
public class EmailUtil {
	private static final Logger LOGGER = Logger.getLogger(EmailUtil.class);

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * This method will send email
	 * @param	- recipientName
	 * @param   - recipientEmail
	 * @param	- locale
	 * @throws	- MessagingException
	 */
	public void sendSimpleMail(final String recipientName,
			final String recipientEmail, final Locale locale,
			Map<String, String> hmEmailParams)
			throws MessagingException
	{
		// Prepare message using a Spring helper
		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
				"UTF-8");

		String subject = hmEmailParams.get("EmailSubject");

		message.setSubject(subject);

		String fromAddr = hmEmailParams.get("EmailFrom");

		message.setFrom(fromAddr);

		message.setTo(recipientEmail);

		message.setText(hmEmailParams.get("EmailBody").replace("\n",  "<br/>"),
				true);

		// Send email
		this.mailSender.send(mimeMessage);
	}

	/**
	 * Method to send an Email
	 * @param hmEmailParams				HashMap holding values for sending email
	 * @param mailFmt					Boolean to indicate whether html mail is
	 * 									to be sent or plain text mail
	 * @throws ServiceException			In case when messaging exception or
	 * 									addresses not provided
	 */
	public void sendEmail(Map<String, String> hmEmailParams, boolean mailFmt)
			throws ServiceException
	{
		final String methodName= ".sendEmail()";
		String retPath = "Return-path";
		String frndFrom = "Friendly-From";
		String frndTo = "Friendly-To";
		String noEmailAddrMsg =
				"No email addresses found. Aborting sending email operation.";
		StringBuilder mailBody = new StringBuilder();
		Long longStartTime = null;

		final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper msg = new MimeMessageHelper(mimeMessage,
				"UTF-8");

		LoggerUtil.logDebug(methodName+ " | Entry", LOGGER);

		longStartTime = System.currentTimeMillis();
		LoggerTimingUtil.logTiming("EmailUtil | sendEmail | Start | From: "
				+ hmEmailParams.get("EmailFrom"), longStartTime);

		try
		{
			msg.setFrom(hmEmailParams.get("EmailFrom"));

			if (!StringUtils.isNullOrBlank(hmEmailParams.get("EmailTo")))
			{
				String[] toAddrArray = hmEmailParams.get("EmailTo").split(",");
				msg.setTo(toAddrArray);
			}

			if (!StringUtils.isNullOrBlank(hmEmailParams.get("EmailCC")))
			{
				String[] ccAddrArray = hmEmailParams.get("EmailCC").split(",");
				msg.setCc(ccAddrArray);
			}

			if (!StringUtils.isNullOrBlank(hmEmailParams.get("EmailBCC")))
			{
				String[] bccAddrArray = hmEmailParams.get("EmailBCC").split(",");
				msg.setBcc(bccAddrArray);
			}

			msg.setSubject(hmEmailParams.get("EmailSubject").toString());
			msg.setPriority(3);

			if (mailFmt)
			{
				hmEmailParams.put("EmailColor", "black");

				mailBody.append("<font color=\"")
						.append(hmEmailParams.get("EmailColor")).append("\"");

				if (null != hmEmailParams.get("EmailFontSize"))
				{
					mailBody.append(" size=\"");
					mailBody.append(hmEmailParams.get("EmailFontSize"));
					mailBody.append("\"");
				}

				if (null != hmEmailParams.get("EmailFont"))
				{
					//mailBody.append("verdana");
					//}
					//else
					//{
					mailBody.append(" face=\"");
					mailBody.append(hmEmailParams.get("EmailFont"));
					mailBody.append("\"");
				}

				mailBody.append(">");

				mailBody.append(hmEmailParams.get("EmailBody").
						replace("\n", "<br/>"));

				mailBody.append("</font>");

				msg.setText(mailBody.toString(), mailFmt);
			}
			else
			{
				msg.setText(hmEmailParams.get("EmailBody"));
			}

			if (!StringUtils.isNullOrBlank(hmEmailParams.
					get("EmailReplyTo")))
			{
				msg.setReplyTo(hmEmailParams.get("EmailReplyTo"));
			}

			if (!StringUtils.isNullOrBlank(hmEmailParams
					.get("EmailReturnPath")))
			{
				mimeMessage.addHeader(retPath,
						hmEmailParams.get("EmailReturnPath"));
			}

			if (!StringUtils.isNullOrBlank(hmEmailParams
					.get("EmailFriendlyFrom")))
			{
				mimeMessage.addHeader(frndFrom,
						hmEmailParams.get("EmailFriendlyFrom"));
			}

			if (!StringUtils.isNullOrBlank(hmEmailParams
					.get("EmailFriendlyTo")))
			{
				mimeMessage.addHeader(frndTo,
						hmEmailParams.get("EmailFriendlyTo"));
			}

			if (!StringUtils.isNullOrBlank(hmEmailParams.get("EmailTo"))
					|| !StringUtils.isNullOrBlank(hmEmailParams
					.get("EmailCC"))
					|| !StringUtils.isNullOrBlank(hmEmailParams
					.get("EmailBCC")))
			{
				this.mailSender.send(mimeMessage);
			}
			else
			{
				throw new ServiceException(6001, noEmailAddrMsg);
			}
		}
		catch (Exception sysExcp)
		{
			sysExcp.printStackTrace();
			throw new ServiceException(6001, sysExcp);
		}

		longStartTime = System.currentTimeMillis();
		LoggerTimingUtil.logTiming("EmailUtil | sendEmail | End | From: "
				+ hmEmailParams.get("EmailFrom"), longStartTime);

		LoggerUtil.logDebug(methodName+ " | Exit", LOGGER);
	}
}