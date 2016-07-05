/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.invoicing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.sharedkernel.Money;


public class Invoice  {

	private ClientData client;

	private Money net;

	private Money gros;

	private List<InvoiceLine> items;


	private Id id;

	public Invoice(InvoiceBuilder invoiceBuilder) {
		id = invoiceBuilder.id;
		client = invoiceBuilder.client;
		net = invoiceBuilder.net;
		gros = invoiceBuilder.gros;
		items = invoiceBuilder.items;
	}

	/**
	 * 
	 * @return immutable projection
	 */
	public List<InvoiceLine> getItems() {
		return Collections.unmodifiableList(items);
	}

	public ClientData getClient() {
		return client;
	}

	public Money getNet() {
		return net;
	}

	public Money getGros() {
		return gros;
	}

	public static class InvoiceBuilder {
		private final Id id;

		private final ClientData client;

		private Money net;

		private Money gros;

		private List<InvoiceLine> items;

		public InvoiceBuilder(ClientData clientData) {
			client = clientData;
			id = Id.generate();
			net = Money.ZERO;
			gros = Money.ZERO;
			items = new ArrayList<InvoiceLine>();
		}

		public InvoiceBuilder addItem(InvoiceLine item) {
			items.add(item);

			net = net.add(item.getNet());
			gros = gros.add(item.getGros());

			return this;
		}

		public Invoice build(){
			return new Invoice(this);
		}

	}
}
