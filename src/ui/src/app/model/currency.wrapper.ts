import { Code } from './currency.rate';

export class CurrencyWrapper {
  date!: Date;
  amount: number = 0.0;
  from!: Code;
  to!: Code;
}
