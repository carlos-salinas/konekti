/*
 * Copyright 2011 Thingtrack, S.L.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.thingtrack.konekti.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Thingtrack S.L.
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="FEEDBACK_TYPE")
public class FeedbackType implements Serializable {
	@Id
	@Column(name="FEEDBACK_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer feedbackTypeId;
	
	@Column(name="CODE", nullable=false, unique=true, length=256)
	@Size(min=1, max=256)
	@NotNull
	private String code;
	
	@Column(name="DESCRIPTION", length=1024)
	@Size(min=1, max=1024)
	private String description;

	/**
	 * @param feedbackTypeId the feedbackTypeId to set
	 */
	public void setFeedbackTypeId(Integer feedbackTypeId) {
		this.feedbackTypeId = feedbackTypeId;
	}

	/**
	 * @return the feedbackTypeId
	 */
	public Integer getFeedbackTypeId() {
		return feedbackTypeId;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((feedbackTypeId == null) ? 0 : feedbackTypeId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FeedbackType))
			return false;
		FeedbackType other = (FeedbackType) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (feedbackTypeId == null) {
			if (other.feedbackTypeId != null)
				return false;
		} else if (!feedbackTypeId.equals(other.feedbackTypeId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FeedbackType [feedbackTypeId=" + feedbackTypeId + ", code="
				+ code + ", description=" + description + "]";
	}
}
