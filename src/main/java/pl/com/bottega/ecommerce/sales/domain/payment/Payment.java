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
package pl.com.bottega.ecommerce.sales.domain.payment;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class Payment {

    private ClientData clientData;

    private Money amount;

    private Id aggregateId;

    public Payment(PaymentBuilder paymentBuilder) {
        this.aggregateId = paymentBuilder.aggregateId;
        this.clientData = paymentBuilder.clientData;
        this.amount = paymentBuilder.amount;
    }

    public Payment rollBack() {
        Id id = Id.generate();

        return new Payment.PaymentBuilder(id, clientData, amount.multiplyBy(-1))
                .build();
    }

    public static class PaymentBuilder {
        private final ClientData clientData;
        private final Money amount;
        private final Id aggregateId;

        public PaymentBuilder(Id aggregateId, ClientData clientData, Money money) {
            this.clientData = clientData;
            this.amount = money;
            this.aggregateId = aggregateId;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
